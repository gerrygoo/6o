package mx.itesm.rmr.fan_recyclerview;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaRVFrag extends Fragment {

    private RecyclerView libros;
    private EditText etTitulo;

    public ListaRVFrag() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        etTitulo = getActivity().findViewById(R.id.etTitulo);

        getActivity().findViewById(R.id.btnBuscar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargarLibros();
            }
        });
    }

    private void descargarLibros() {
        String titulo = etTitulo.getText().toString();

        // Para ocultar el teclado
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etTitulo.getWindowToken(), 0);

        // Descargar JSON del libro
        String url = "https://www.googleapis.com/books/v1/volumes";
        AndroidNetworking.get(url)
                .addQueryParameter("q", "title:" +  titulo)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if ( response.has("items") ){
                            try {
                                JSONArray arr = response.getJSONArray("items");

                                String[] arrT = new String[arr.length()];
                                String[] arrD = new String[arr.length()];

                                for (int i = 0; i < arr.length(); i++){
                                    JSONObject volumeInfo = arr.getJSONObject(i).getJSONObject("volumeInfo");
                                    arrT[i] = volumeInfo.getString("title");
                                    arrD[i] = volumeInfo.has("description") ? volumeInfo.getString("description") : "No hay descripcion ):" ;
                                }

                                AdaptadorRV adaptador = new AdaptadorRV(arrT, arrD);
                                libros.setAdapter(adaptador);

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        //message no response
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista_rv, container, false);

        libros = v.findViewById(R.id.rvLibros);

        String titulos[] = {"A", "B"};
        String descripciones[] = {"x", "y"};

        AdaptadorRV adaptador = new AdaptadorRV(titulos, descripciones);

        libros.setAdapter(adaptador);
        libros.setLayoutManager( new LinearLayoutManager( getContext() ) );

        return v;
    }

}
