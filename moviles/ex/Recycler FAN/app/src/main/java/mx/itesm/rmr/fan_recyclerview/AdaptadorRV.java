package mx.itesm.rmr.fan_recyclerview;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by gerry on 2/21/18.
 */

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.Vista>{

    //Datos de adaptador
    private String[] titulos;
    private String[] descripciones;

    public AdaptadorRV(String[] titulos, String[] descripciones){
        this.titulos = titulos;
        this.descripciones = descripciones;
    }

    @Override
    public Vista onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView tarjeta = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_libro, parent, false);
        return new Vista(tarjeta);
    }

    @Override
    public void onBindViewHolder(Vista holder, int position) {
        CardView tarjeta = holder.tarjeta;

        TextView tvTitulo = tarjeta.findViewById(R.id.tvTitulo);
        TextView tvDescripcion = tarjeta.findViewById(R.id.tvDescripcion);

        tvTitulo.setText( titulos[position] );
        tvDescripcion.setText( descripciones[position] );
    }

    @Override
    public int getItemCount() {
        return titulos.length;
    }

    public class Vista extends RecyclerView.ViewHolder {
        private CardView tarjeta;

        public Vista(CardView v){
            super(v);
            this.tarjeta = v;
        }
    }
}
