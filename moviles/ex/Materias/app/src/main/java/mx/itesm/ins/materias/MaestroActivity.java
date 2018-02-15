package mx.itesm.ins.materias;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MaestroActivity extends AppCompatActivity implements ListaMateriasFrag.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maestro);
    }

    @Override
    public void ItemClicked(long id) {
        View v = findViewById(R.id.fragContenedor);
        if( v == null ){
            //phone
            Intent intDetalle = new Intent(this, DetalleActivity.class);
            intDetalle.putExtra("id", id);
            startActivity(intDetalle);
        }else{
            //tablet
            MateriaDetalleFragment fragDetalle = new MateriaDetalleFragment();
            fragDetalle.setId(id);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragContenedor, fragDetalle);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
