package mx.itesm.rmr.practicabd;

import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by roberto on 04/03/18.
 */

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.Vista>
{
    // DATOS
    private String[] arrMatriculas;
    private String[] arrNombres;
    private String[] arrCarreras;
    private String[] arrCorreos;
    private Bitmap[] arrFotos;

    public AdaptadorRV(String[] matriculas, String[] nombres, String[] carreras, String[] correos, Bitmap[] fotos) {
        arrMatriculas = matriculas;
        arrNombres = nombres;
        arrCarreras = carreras;
        arrCorreos = correos;
        arrFotos = fotos;
    }

    public void setDatos(String[] matriculas, String[] nombres, String[] carreras, String[] correos, Bitmap[] fotos) {
        arrMatriculas = matriculas;
        arrNombres = nombres;
        arrCarreras = carreras;
        arrCorreos = correos;
        arrFotos = fotos;
    }

    @Override
    public Vista onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView tarjeta = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tarjeta_alumno, parent, false);

        return new Vista(tarjeta);  // Un renglón de la lista
    }

    @Override
    public void onBindViewHolder(Vista holder, int position) {
        CardView tarjeta = holder.tarjeta;
        TextView tvMatricula = tarjeta.findViewById(R.id.tvMatricula);
        TextView tvNombre = tarjeta.findViewById(R.id.tvNombre);
        TextView tvCarrera = tarjeta.findViewById(R.id.tvCarrera);
        TextView tvCorreo = tarjeta.findViewById(R.id.tvCorreo);
        ImageView ivFoto = tarjeta.findViewById(R.id.ivFoto);

        tvMatricula.setText(arrMatriculas[position]);
        tvNombre.setText(arrNombres[position]);
        tvCarrera.setText(arrCarreras[position]);
        tvCorreo.setText(arrCorreos[position]);
        ivFoto.setImageBitmap(arrFotos[position]);
    }


    @Override
    public int getItemCount() {
        return arrMatriculas.length;
    }

    public class Vista extends RecyclerView.ViewHolder {
        private CardView tarjeta;

        public Vista(CardView v) {
            super(v);
            this.tarjeta = v;
        }
    }
}