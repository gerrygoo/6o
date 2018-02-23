package mx.itesm.rmr.fan_recyclerview;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultaLibroFrag extends Fragment
{
    private EditText etISBN;
    private TextView tvContenido;

    public ConsultaLibroFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consulta_libro, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        etISBN = getActivity().findViewById(R.id.etISBN);
        tvContenido = getActivity().findViewById(R.id.tvContenido);

        Button btnConsultar = getActivity().findViewById(R.id.btnConsultaISBN);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarLibro();
            }
        });
    }

    public void consultarLibro() {
        String isbn = etISBN.getText().toString();
        // Para ocultar el teclado
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etISBN.getWindowToken(), 0);

        // Descargar JSON del libro
        String url = "https://www.googleapis.com/books/v1/volumes";
        AndroidNetworking.get(url)
                .addQueryParameter("q", "isbn:" +  isbn)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if ( response.has("items") ){
                            try {
                                JSONArray arr = response.getJSONArray("items");
                                JSONObject volumeInfo = arr.getJSONObject(0).getJSONObject("volumeInfo");
                                tvContenido.setText(
                                        volumeInfo.getString("title") + "\n\n"
                                        + volumeInfo.getString("description")
                                );
                                descargarImagen(volumeInfo.getJSONObject("imageLinks").getString("thumbnail"));

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }else{
                            tvContenido.setText("No existe ISBN.");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        tvContenido.setText("Servidor no responde.");
                    }
                });
                /*.getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });*/
    }

    private void descargarImagen(String urlImg) {
        AndroidNetworking.get(urlImg)
                .setTag("imageRequestTag")
                .setPriority(Priority.MEDIUM)
                .setBitmapMaxHeight(640)
                .setBitmapMaxWidth(480)
                .setBitmapConfig(Bitmap.Config.ARGB_8888)
                .build()
                .getAsBitmap(new BitmapRequestListener() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ImageView img = getActivity().findViewById(R.id.imgPortada);
                        img.setImageBitmap(bitmap);
                    }
                    @Override
                    public void onError(ANError error) {
                        tvContenido.setText(error.getLocalizedMessage() + ", "+error.getErrorDetail());
                    }
                });
    }
}
