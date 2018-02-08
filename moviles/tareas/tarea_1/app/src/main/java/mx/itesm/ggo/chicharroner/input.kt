package mx.itesm.ggo.chicharroner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_input.*
import java.math.BigDecimal

class input : AppCompatActivity() {

    private lateinit var r1: EditText
    private lateinit var r2: EditText
    private lateinit var r3: EditText
    private lateinit var x1: TextView
    private lateinit var x2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        r1 = editTextA
        r2 = editTextB
        r3 = editTextC

        x1 = textX1
        x2 = textX2

        r1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { updateEverything() }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        r2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { updateEverything() }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        r3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { updateEverything() }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    fun Double.roundTo2DecimalPlaces() =
            BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()

    fun updateEverything(){
        var a = if ( r1.text.isEmpty() ) 0.0 else r1.text.toString().toDouble()
        var b = if ( r2.text.isEmpty() ) 0.0 else r2.text.toString().toDouble()
        var c = if ( r3.text.isEmpty() ) 0.0 else r3.text.toString().toDouble()

        var root1 = (-b + Math.sqrt( b*b - 4.0 * a * c ) )/ (2*a)
        var root2 = (-b - Math.sqrt( b*b - 4.0 * a * c ) )/ (2*a)

        x1.text = if (a == 0.0) "Indefinido" else if ( root1.isNaN() ) "Raiz Compleja" else root1.roundTo2DecimalPlaces().toString()
        x2.text = if (a == 0.0) "Indefinido" else if ( root2.isNaN() ) "Raiz Compleja" else root2.roundTo2DecimalPlaces().toString()
    }

}


