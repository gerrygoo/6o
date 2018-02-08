package mx.itesm.ggo.juegoppt

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class info : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val puntosCompu = intent.getIntExtra("puntosCompu", 0);
        val puntosJugador = intent.getIntExtra("puntosJugador", 0);

        val mensaje = "Compu: $puntosCompu, Jugador: $puntosJugador"
    }

    override fun onBackPressed() {
        val intRegreso = Intent()

        intRegreso.putExtra("valor", Math.random().toInt())
        setResult(Activity.RESULT_OK, intRegreso)

        super.onBackPressed()

    }
}
