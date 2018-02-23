package mx.itesm.ggo.isbn

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_isbn.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class IsbnActivity : AppCompatActivity() {

    val API = "https://www.googleapis.com/books/v1/volumes?q=isbn:"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_isbn)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 500)
        }

        searchbutton.setOnClickListener { _ -> DescargaTextoTarea().execute(API + isbntview.text.toString() ) }
    }

    @Throws(IOException::class)
    private fun abrirConexion(direccionRecurso: String): InputStream? {
        var flujoEntrada: InputStream? = null
        val url = URL(direccionRecurso)
        val conexion = url.openConnection() as? HttpURLConnection ?: throw IOException("No es una conexion HTTP")

        try {
            val httpConn = conexion
            httpConn.setAllowUserInteraction(false)
            httpConn.setInstanceFollowRedirects(true)
            httpConn.setRequestMethod("GET")
            httpConn.connect()
            val respuesta = httpConn.getResponseCode() // Respuesta

            if (respuesta == HttpURLConnection.HTTP_OK) {
                flujoEntrada = httpConn.getInputStream()
            }
        } catch (e: Exception) {
            Log.d("Networking", e.localizedMessage)
            throw IOException("Error conectando a: " + direccionRecurso)
        }

        return flujoEntrada
    }

    private fun descargarTexto(direccion: String): String {
        val tamBuffer = 2000   // Paquetes de texto
        var flujoEntrada: InputStream? = null
        try {
            flujoEntrada = abrirConexion(direccion)  // Estable y abre la conexiÃ³n
        } catch (e: IOException) {
            return "Error en la descarga de " + direccion
        }

        // Lectura 'normal', como cualquier flujo de entrada
        val isr = InputStreamReader(flujoEntrada)

        val contenido = StringBuffer()
        var buffer = CharArray(tamBuffer)

        var numCharLeidos: Int = isr.read(buffer)

        try {
            while (numCharLeidos  > 0) {    // Mientras lee caracteres
                //convierte el arreglo de caracteres en cadena
                val cadena = String(buffer, 0, numCharLeidos)
                contenido.append(cadena)
                buffer = CharArray(tamBuffer)
                numCharLeidos = isr.read(buffer)
            }
            flujoEntrada!!.close()
        } catch (e: IOException) {
            return "Error leyendo los datos"
        }

        return contenido.toString()
    }

    private inner class DescargaTextoTarea : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg urls: String): String {
            return descargarTexto(urls[0]) // Descarga el contenido
        }

        override fun onPostExecute(result: String) {

            try{

                var dict = JSONObject(result)



                if ( dict.getInt("totalItems") > 0){
                    var bookObj = dict.getJSONArray("items")[0] as JSONObject
                    var volumeInfo = bookObj.getJSONObject("volumeInfo")

                    titletview.setText( volumeInfo.getString("title") )

                    authortview.setText( volumeInfo.getString("authors").toString().replace("[", "").replace("]", "") )

                    editorialtview.setText( volumeInfo.getString("publisher") )

                    yeartview.setText( volumeInfo.getString("publishedDate") )

                    descriptiontview.setText( volumeInfo.getString( "description" ) )

                    DescargaImagenTarea().execute( volumeInfo.getJSONObject("imageLinks").getString("smallThumbnail")  )
                }else{
                    titletview.setText( "ISBN no existe")

                    authortview.setText( "ISBN no existe")

                    editorialtview.setText( "ISBN no existe")

                    yeartview.setText( "ISBN no existe")

                    descriptiontview.setText( "ISBN no existe")

                    DescargaImagenTarea().execute( "ISBN no existe")
                }



            }catch( e: JSONException){
                e.printStackTrace()
            }
        }
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
            imageView.setImageBitmap(result)
        }
    }
}
