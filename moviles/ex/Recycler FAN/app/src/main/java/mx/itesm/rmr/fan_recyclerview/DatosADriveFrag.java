package mx.itesm.rmr.fan_recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatosADriveFrag extends Fragment
{
    private EditText etNombre;
    private EditText etEdad;

    public DatosADriveFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos_adrive, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        etNombre = getActivity().findViewById(R.id.etNombre);
        etEdad = getActivity().findViewById(R.id.etEdad);
        Button btnEnviar = getActivity().findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarADrive();
            }
        });
    }

    private void enviarADrive() {

        String url = "https://docs.google.com/forms/d/e/1FAIpQLSeILSKn4g4hN2zLTXR57r-ODy-J5kn0aKDEo8RwhGYPb54qsw/formResponse";
        AndroidNetworking.post(url)
                .addBodyParameter("entry.1355070686", etNombre.getText().toString() )
                .addBodyParameter("entry.1983742216", etEdad.getText().toString() )
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
