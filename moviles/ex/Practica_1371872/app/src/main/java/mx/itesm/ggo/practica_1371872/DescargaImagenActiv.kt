package mx.itesm.ggo.practica_1371872

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.os.AsyncTask
import android.graphics.BitmapFactory
import java.net.HttpURLConnection.HTTP_OK
import android.widget.Toast
import android.content.pm.PackageManager
import android.support.annotation.NonNull
import android.Manifest.permission
import android.Manifest.permission.INTERNET
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_descarga_imagen.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class DescargaImagenActiv : AppCompatActivity() {
    private val SOLICITA_INTERNET = 500
    val dir = "https://appmovil.cem.itesm.mx/rmroman/sorpresa.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descarga_imagen)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),
                    SOLICITA_INTERNET)
        } else {
            DescargaImagenTarea().execute(dir)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            SOLICITA_INTERNET -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                DescargaImagenTarea().execute(dir)
            } else {
                Toast.makeText(this, "Concede el permiso de INTERNET", Toast.LENGTH_SHORT).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    @Throws(IOException::class)
    private fun abrirConexion(direccionRecurso: String): InputStream? {
        var flujoEntrada: InputStream? = null
        val url = URL(direccionRecurso)
        // Crea el enlace de comunicaciÃ³n entre la app y el url
        val conexion = url.openConnection() as? HttpURLConnection
                ?: throw IOException("No es una conexiÃ³n HTTP")

        try {
            val httpConn = conexion as HttpURLConnection
            httpConn.setAllowUserInteraction(false)
            httpConn.setInstanceFollowRedirects(true)
            httpConn.setRequestMethod("GET")
            httpConn.connect() // Abre el enlace de comunicaciÃ³n
            val respuesta = httpConn.getResponseCode() // Respuesta

            if (respuesta == HttpURLConnection.HTTP_OK) {
                // Exito en la conexiÃ³n
                flujoEntrada = httpConn.getInputStream() // Obtenemos el flujo para leer los datos
            }
        } catch (e: Exception) {
            throw IOException("Error conectando a: " + direccionRecurso)
        }

        return flujoEntrada    // Entrega el flujo que ya se puede leer
    }

    private fun descargarImagen(direccion: String): Bitmap? {
        var bitmap: Bitmap? = null

        try {
            val flujoEntrada = abrirConexion(direccion)
            bitmap = BitmapFactory.decodeStream(flujoEntrada)
            flujoEntrada!!.close()
        } catch (e: Exception) {
            Log.i("descargarImagen", "EXCEPCION: " + e)
        }

        return bitmap
    }


    private inner class DescargaImagenTarea : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            return descargarImagen(urls[0])
        }

        override fun onPostExecute(result: Bitmap) {
            ivFoto.setImageBitmap(result)
        }
    }
}