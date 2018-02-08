package mx.itesm.ggo.juegoppt

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ppt.*

class PPTActiv : AppCompatActivity() {

    lateinit var ppt: PiedraPapelTijeras;
    lateinit var etCompu: EditText;
    lateinit var etJugador: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ppt)

        //view
        ppt = PiedraPapelTijeras()

        //model
        etCompu =  et_compu
        etJugador = et_jugador

        btn_papel.setOnClickListener { v -> jugar(v)}
        btn_piedra.setOnClickListener { v -> jugar(v)}
        btn_tijeras.setOnClickListener { v -> jugar(v)}
        btn_info.setOnClickListener { v -> mostrarAcercaDe(v) }
    }

    //events
    fun jugar(v: View){
        var jugadaJugador: Jugada = Jugada.PIEDRA;
        if(v.id == btn_papel.id) jugadaJugador = Jugada.PAPEL
        if(v.id == btn_tijeras.id) jugadaJugador = Jugada.TIJERAS

        val jugadaCompu: Jugada = ppt.juegoAzar();

        val resultado: Resultado = ppt.jugar(jugadaJugador, jugadaCompu)

        val mensaje: String = "Compu juega con " + jugadaCompu + ", " + resultado

        //Toast.makeText(this, mensaje, Toast.LENGTH_SHORT)
        Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG).show()

        actualizarPuntajes()
    }

    private fun  actualizarPuntajes(){
        etCompu.setText(ppt.puntosCompu.toString())
        etJugador.setText(ppt.puntosJugador.toString())
    }

    private fun mostrarDialogo(m: String){}

    fun mostrarAcercaDe(v: View){
        val msg = "Compu: ${ppt.puntosCompu}, Jugador: ${ppt.puntosJugador}"
        val intShare = Intent(Intent.ACTION_SEND)
        intShare.type = "text/plain"
        intShare.putExtra(Intent.EXTRA_TEXT, msg)
        intShare.putExtra(Intent.EXTRA_SUBJECT, "Mi marcador!, viejo!")
        startActivity(intShare)


        //val selector = Intent.createChooser(intShare, "Selecciona")
        //startActivity(selector)

        /*val acercaDe = Intent(this, info::class.java)
        //val mensaje = "Computadora: " + ppt.puntosCompu.toString()
        acercaDe.putExtra("puntosCompu", ppt.puntosCompu)
        acercaDe.putExtra("puntosJugador", ppt.puntosCompu)
        //startActivity(acercaDe)
        startActivityForResult(acercaDe, CODIGOS.PEDIR_ENTERO.ordinal)*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if( requestCode == CODES.PEDIR_ENTERO.ordinal ){
            if(resultCode == Activity.RESULT_OK){
                var puntosCompu = intent.getIntExtra("valor", 0)
                Toast.makeText(this, "Mensaje: ${puntosCompu.toString()}", Toast.LENGTH_SHORT)
            }
        }
    }





}
