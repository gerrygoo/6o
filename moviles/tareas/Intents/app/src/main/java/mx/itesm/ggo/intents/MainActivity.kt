package mx.itesm.ggo.intents

import android.app.Activity
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.AlarmClock
import android.R.attr.phoneNumber
import android.provider.MediaStore
import android.graphics.Bitmap
import android.R.attr.data
import android.support.v4.app.NotificationCompat.getExtras









class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmbtn.setOnClickListener { _ -> alarm() }
        photobtn.setOnClickListener { _ -> photo() }
        urlbtn.setOnClickListener { _ -> url() }
        mapbtn.setOnClickListener { _ -> map() }
        callbtn.setOnClickListener { _ -> call() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        Log.i("Executed", "onActivityResult" + requestCode.toString())
        if( resultCode == Activity.RESULT_OK ){
            when(requestCode){

                CODES.ALARM.ordinal -> {
                    val hour = data.getStringExtra("txta")
                    val minutes = data.getStringExtra("txtb")
                    val go = Intent(AlarmClock.ACTION_SET_ALARM)
                            .putExtra(AlarmClock.EXTRA_HOUR, hour.toInt())
                            .putExtra(AlarmClock.EXTRA_MINUTES, minutes.toInt())
                    if (go.resolveActivity(getPackageManager()) != null) {
                        startActivity(go);
                    }

                }

                CODES.PHOTO.ordinal ->  {
                    val extras = data.extras
                    val imageBitmap = extras.get("data") as Bitmap
                    imageview.setImageBitmap(imageBitmap)
                }

                CODES.URL.ordinal -> {
                    val url = data.getStringExtra("txta")
                    val webpage = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, webpage)
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                }

                CODES.MAP.ordinal -> {
                    val x = data.getStringExtra("txta").toFloat()
                    val y = data.getStringExtra("txtb").toFloat()
                    val loc = Uri.parse("geo: $x, $y")
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = loc
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                }

                CODES.CALL.ordinal -> {
                    val phoneNumber = data.getStringExtra("txta")
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:" + phoneNumber)
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                }

                else -> Log.e("Error", "Unrecognized code")
            }
        }




    }

    private fun alarm(){
        val go = Intent(this, AlarmAct::class.java)
        go.putExtra("instructions", "Introduce hora / minuto")
        go.putExtra("defaultA", (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)).toString() )
        go.putExtra("defaultB", (Calendar.getInstance().get(Calendar.MINUTE)+2).toString())

        startActivityForResult(go, CODES.ALARM.ordinal)
    }

    private fun photo(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, CODES.PHOTO.ordinal)
        }
    }

    private fun url(){
        val go = Intent(this, UrlAct::class.java)
        go.putExtra("instructions", "Introduce url")
        go.putExtra("defaultA", "https://developer.android.com/index.html")
        startActivityForResult(go, CODES.URL.ordinal)
    }

    private fun map(){
        val go = Intent(this, AlarmAct::class.java)
        go.putExtra("instructions", "Coordenada X / Y")
        go.putExtra("defaultA", "19.597400")
        go.putExtra("defaultB", "-99.226727")
        startActivityForResult(go, CODES.MAP.ordinal)
    }

    private fun call(){
        val go = Intent(this, UrlAct::class.java)
        go.putExtra("instructions", "Introduce teléfono")
        go.putExtra("defaultA", "5558645555")
        startActivityForResult(go, CODES.CALL.ordinal)
    }

}
