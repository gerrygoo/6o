package mx.itesm.rmr.practicabd;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import mx.itesm.rmr.practicabd.basedatos.BaseDatos;
import mx.itesm.rmr.practicabd.basedatos.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFrag extends Fragment
{
    private RecyclerView rvAlumnos;
    // Arreglos para el adaptador
    private String[] arrMatriculas;
    private String[] arrNombres;
    private String[] arrCorreos;
    private String[] arrCarreras;
    private Bitmap[] arrFotos;


    public ListaFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_lista, container, false);
        // Adaptador de prueba vacío
        rvAlumnos = v.findViewById(R.id.rvAlumnos);
        AdaptadorRV adaptador = new AdaptadorRV(new String[]{}, new String[]{}, new String[]{}, new String[]{}, null);
        rvAlumnos.setAdapter(adaptador);
        rvAlumnos.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();

        new BDTarea().execute();
    }


    private void cargarDatos() {
        // BD
        BaseDatos bd = BaseDatos.getInstance(getContext());
        int numAlumnos = bd.alumnoDAO().contarAlumnos();
        Log.i("cragarDatos", "Registros: " + numAlumnos);
        List<User> alumnos = bd.alumnoDAO().leerTodos();
        // Crea los arreglos para el adaptador
        arrMatriculas = new String[numAlumnos];
        arrNombres = new String[numAlumnos];
        arrCorreos = new String[numAlumnos];
        arrCarreras = new String[numAlumnos];
        arrFotos = new Bitmap[numAlumnos];
        // Procesa cada registro
        for (int i=0; i<alumnos.size(); i++) {
            User a = alumnos.get(i);
            arrMatriculas[i] = a.getUid()+"";
            arrNombres[i] = a.getName();
            arrCarreras[i] = a.getCv();
            arrCorreos[i] = a.getProjects();

            Log.i("BD", a.toString());

            arrFotos[i] = decodificarImagen(a);
        }

        BaseDatos.destroyInstance();
    }

    // Crea el bitmap a partir del arreglo de bytes
    @NonNull
    private Bitmap decodificarImagen(User a) {
        Bitmap bm = null;
        try {
            InputStream ent = getActivity().getResources().getAssets().open("temp.png");
            bm = BitmapFactory.decodeStream(ent);
        } catch (IOException e) {
            Log.i("cargaBD", "Error: " + e.getMessage());
        }
        int width = 128;
        int height = 128;
        Bitmap.Config configBmp = Bitmap.Config.valueOf(bm.getConfig().name());
        Bitmap bitmap_tmp = Bitmap.createBitmap(width, height, configBmp);
        ByteBuffer buffer = ByteBuffer.wrap(a.getFoto());
        bitmap_tmp.copyPixelsFromBuffer(buffer);
        return bitmap_tmp;
    }

    // Para cargar los datos en segundo plano
    class BDTarea extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            cargarDatos();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Nuevos datos para el adaptador
            AdaptadorRV adaptador = (AdaptadorRV)rvAlumnos.getAdapter();
            adaptador.setDatos(arrMatriculas, arrNombres, arrCarreras, arrCorreos, arrFotos);
            adaptador.notifyDataSetChanged();
        }
    }
}
