package mx.itesm.ins.materias;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MateriaDetalleFragment extends Fragment {

    private long id;

    public void setId(long id){
        this.id = id;
    }

    public MateriaDetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong("ID", id);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( savedInstanceState != null ) id = savedInstanceState.getLong("ID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_materia_detalle, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        if(v != null){
            TextView textviewMateria = v.findViewById(R.id.textviewDetalle);
            Materia materia = Materia.materias[(int)id];
            textviewMateria.setText(materia.getDescripcion());
        }
    }
}
