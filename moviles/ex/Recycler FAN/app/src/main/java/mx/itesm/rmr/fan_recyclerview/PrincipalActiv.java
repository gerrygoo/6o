package mx.itesm.rmr.fan_recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class PrincipalActiv extends AppCompatActivity
{

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_consulta_isbn:
                    cargarFragmentoConsultaLibro();
                    return true;
                case R.id.navigation_enviar_drive:
                    cargarFragmentoEnviarDrive();
                    return true;
                case R.id.navigation_recycler:
                    cargarFragmentoLibros();
                    return true;
            }
            return false;
        }
    };

    private void cargarFragmentoLibros() {
        ListaRVFrag listaRVFrag = new ListaRVFrag();

        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
        transaccion.replace(R.id.layoutPrincipal, listaRVFrag);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    private void cargarFragmentoEnviarDrive() {
        DatosADriveFrag fragADrive = new DatosADriveFrag();

        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
        transaccion.replace(R.id.layoutPrincipal, fragADrive);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    private void cargarFragmentoConsultaLibro() {
        ConsultaLibroFrag fragConsulta = new ConsultaLibroFrag();

        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
        transaccion.replace(R.id.layoutPrincipal, fragConsulta);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
