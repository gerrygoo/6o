package mx.itesm.rmr.practicabd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class PrincipalActiv extends AppCompatActivity
{
    private boolean estaEnCaptura;  // Para saber qué fragmento mostrar

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_captura:
                    if (!estaEnCaptura) {   // Si no está mostrando el fragmento, lo muestra
                        CapturaFrag fragCaptura = new CapturaFrag();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frContenedor, fragCaptura);
                        ft.commit();
                        estaEnCaptura = true;
                    }
                    return true;
                case R.id.menu_lista:
                    if (estaEnCaptura) {
                        ListaFrag fragLista = new ListaFrag();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frContenedor, fragLista);
                        ft.commit();
                        estaEnCaptura = false;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Fragmento inicial (Capturando datos)
        CapturaFrag fragCaptura = new CapturaFrag();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frContenedor, fragCaptura);
        ft.commit();
        estaEnCaptura = true;
    }

}
