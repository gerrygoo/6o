package mx.itesm.rmr.practicabd;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import java.nio.ByteBuffer;

import mx.itesm.rmr.practicabd.basedatos.BaseDatos;
import mx.itesm.rmr.practicabd.basedatos.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class CapturaFrag extends Fragment
{
    public static final int SOLICITA_CAMARA = 500;

    // CAMPOS
    private EditText etMatricula;
    private EditText etNombre;
    private EditText spCarrera;
    private EditText etCorreo;
    private ImageView ivFoto;

    public CapturaFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_captura, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        // Recupera las referencias a los componentes
        etMatricula = getActivity().findViewById(R.id.etMatricula);
        etNombre = getActivity().findViewById(R.id.etNombre);
        spCarrera = getActivity().findViewById(R.id.spCarrera);
        etCorreo = getActivity().findViewById(R.id.etCorreo);
        ivFoto = getActivity().findViewById(R.id.ivFoto);

        // Foto
        Button btnFoto = getActivity().findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la app para tomar la foto
                Intent intFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intFoto.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intFoto, SOLICITA_CAMARA);
                }

            }
        });

        // Botón grabar
        Button btnGrabar = getActivity().findViewById(R.id.btnGrabar);
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BDTarea().execute();
            }
        });
    }

    // Graba los datos en la BD
    private void grabarDatos() {

        User alumno = new User();
        alumno.setUid( etMatricula.getText().toString() );
        alumno.setName(etNombre.getText().toString());
        alumno.setProjects(spCarrera.getText().toString());
        alumno.setCv(etCorreo.getText().toString());
        alumno.setFoto(codificarImagen());

        // BD
        BaseDatos bd = BaseDatos.getInstance(getActivity());
        bd.alumnoDAO().insertarAlumnos(alumno);

        // Muestra el número de registros. Para verificar que graba correctamente.
        Log.i("onResume", "Registros: " + bd.alumnoDAO().contarAlumnos());
        BaseDatos.destroyInstance();
    }

    // Convierte la imagen del ImageView (ivFoto) a un arreglo de bytes
    // ES MEJOR guardar los datos comprimidos :)
    private byte[] codificarImagen() {
        Bitmap bm = ((BitmapDrawable)ivFoto.getDrawable()).getBitmap();
        int longitud = bm.getRowBytes() * bm.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(longitud);
        bm.copyPixelsToBuffer(byteBuffer);
        byte[] byteArray = byteBuffer.array();
        return byteArray;
    }

    // Después de tomar la foto, ejecuta este método
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SOLICITA_CAMARA && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bm = (Bitmap) extras.get("data");
            // Todas las imágenes se guardan en tamaño 128x128
            ivFoto.setImageBitmap(Bitmap.createScaledBitmap(bm,128,128,false));
        }
    }


    // Clase para ejecutar código en segundo plano.
    class BDTarea extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            grabarDatos();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i("onPost", "Dato grabado ********************");
        }
    }
}
