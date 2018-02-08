package mx.itesm.ggo.intents

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_url.*

class UrlAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url)

        val instructions = intent.getStringExtra("instructions")
        val defaultA = intent.getStringExtra("defaultA");

        instructiontview.setText(instructions)
        etxta.setText(defaultA)

        backbtn.setOnClickListener { _ -> back()}
    }

    private fun back(){
        val go = Intent()

        go.putExtra("txta", etxta.text.toString() )

        setResult(Activity.RESULT_OK,  go)

        onBackPressed()
    }
}
