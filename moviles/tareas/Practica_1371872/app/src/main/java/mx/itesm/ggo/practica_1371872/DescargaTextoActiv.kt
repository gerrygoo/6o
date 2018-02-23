package mx.itesm.ggo.practica_1371872

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import mx.itesm.ggo.practica_1371872.R.id.tvContenido
import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection.HTTP_OK
import java.net.HttpURLConnection
import java.net.URL
import android.widget.Toast
import android.content.pm.PackageManager
import android.support.annotation.NonNull
import android.widget.TextView
import android.Manifest.permission
import android.Manifest.permission.INTERNET
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_descarga_texto.*
import mx.itesm.ggo.practica_1371872.R.id.tvContenido
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class DescargaTextoActiv : AppCompatActivity() {

    private val DESCARGA_TEXTO = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descarga_texto)


        // Pregunta si NO hay permiso aÃºn
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),
                    DESCARGA_TEXTO)
            // ResponderÃ¡ con el mÃ©todo onRequestPermissionsResult...
        } else {
            // Ya tiene el permiso. INICIA la descarga en segundo plano

            //DescargaTextoTarea().execute("http://www.gutenberg.org/ebooks/2000.txt.utf-8")
            DescargaTextoTarea().execute("https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=hacker&format=json")
        }
    }

    // Se ejecuta despuÃ©s de que el permiso concediÃ³/negÃ³ el permiso
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            DESCARGA_TEXTO -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Ya tiene el permiso. INICIA la descarga en segundo plano

                DescargaTextoTarea().execute("http://www.gutenberg.org/ebooks/2000.txt.utf-8")
                //DescargaTextoTarea().execute("https://en.wikipedia.org/w/ api.php?action=query&list=search&srsearch=hacker&format=json")
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
            Log.d("Networking", e.localizedMessage)
            throw IOException("Error conectando a: " + direccionRecurso)
        }

        return flujoEntrada    // Entrega el flujo que ya se puede leer
    }

    // Descarga un recurso de texto desde la red
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
            //tvContenido.setText(result)    // Al terminar, actualiza la UI
            try{
                var dict = JSONObject(result)

                var dQuery = dict.volumeInfo("query")

                var arrSearch = dQuery.getJSONArray("search")

                var sb = StringBuffer()
                for(i in 0 .. (arrSearch.length()-1)){
                    var dConcepto = arrSearch.getJSONObject(i)

                    sb.append(dConcepto.getString("title"))
                    sb.append("\n")
                    sb.append(dConcepto.getString("snippet"))
                    sb.append("\n")
                }

                tvContenido.setText(sb.toString())
            }catch( e: JSONException){
                e.printStackTrace()
            }
        }
    }
}
