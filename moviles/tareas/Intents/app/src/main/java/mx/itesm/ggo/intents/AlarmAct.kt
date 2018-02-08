package mx.itesm.ggo.intents

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_alarm.*

class AlarmAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        val instructions = intent.getStringExtra("instructions")
        val defaultA = intent.getStringExtra("defaultA");
        val defaultB = intent.getStringExtra("defaultB");

        instructiontview.setText(instructions)
        etxta.setText(defaultA)
        etxtb.setText(defaultB)

        backbtn.setOnClickListener { _ -> back()}
    }

    private fun back(){
        val go = Intent()

        go.putExtra("txta", etxta.text.toString() )
        go.putExtra("txtb", etxtb.text.toString() )

        setResult(Activity.RESULT_OK, go)

        onBackPressed()
    }
}
