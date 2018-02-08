package mx.itesm.ggo.materias

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_maestro.*

class MaestroActiv : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maestro)

        buttonTemp.setOnClickListener { goTo() }
    }

    fun goTo() {
        startActivity( Intent(this, DetalleActiv::class.java) )
    }
}
