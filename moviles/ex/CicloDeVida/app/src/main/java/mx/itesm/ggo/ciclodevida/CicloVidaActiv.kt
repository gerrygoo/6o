package mx.itesm.ggo.ciclodevida

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class CicloVidaActiv : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("cicloVida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("cicloVida", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("cicloVida", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("cicloVida", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("cicloVida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("cicloVida", "onStop")
    }

    override override fun onDestroy() {
        super.onDestroy()
        Log.i("cicloVida", "onDestroy")
    }

}
