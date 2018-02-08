package mx.itesm.ggo.lupa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class Main : AppCompatActivity() {

    var lupa = LuPA(7);
    private lateinit var switches: List<Switch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_reset.setOnClickListener { _ -> reset() }
        btn_info.setOnClickListener { _ -> showInfo()}
        switches = listOf(sw_0, sw_1, sw_2, sw_3, sw_4, sw_5, sw_6)
        switches.forEachIndexed{ i, s -> s.isChecked = lupa.toggles[i] }
        switches.forEach { s -> s.setOnCheckedChangeListener{ v, _ -> onCheck(v) } }
    }

    private fun switcheroo(v: Switch){
        v.setOnCheckedChangeListener(null)
        v.toggle()
        v.setOnCheckedChangeListener{ v, _ -> onCheck(v) }
    }

    private fun reset(){
        lupa.reset()
        switches.forEachIndexed{ i, s -> s.isChecked = lupa.toggles[i] }
        switches.forEach { s -> s.isEnabled = true }
        lupa.moves = 0
        txt_moves.setText(lupa.moves.toString())
    }

    private fun won(): Boolean{
        return sw_0.isChecked and sw_1.isChecked and sw_2.isChecked and sw_3.isChecked and sw_4.isChecked and sw_5.isChecked and sw_6.isChecked
    }

    // because I chose to use toggles for the game: not having the logic embedded in the view was an too complicated so it is here ),:
    private fun onCheck(v: View){
        when(v){
            sw_0 -> {
                switcheroo(sw_1)
            }
            sw_1 -> {
                switcheroo(sw_0)
                switcheroo(sw_2)
            }
            sw_2 -> {
                switcheroo(sw_1)
                switcheroo(sw_3)
            }
            sw_3 -> {
                switcheroo(sw_2)
                switcheroo(sw_4)
            }
            sw_4 -> {
                switcheroo(sw_3)
                switcheroo(sw_5)
            }
            sw_5 -> {
                switcheroo(sw_4)
                switcheroo(sw_6)
            }
            sw_6 -> {
                switcheroo(sw_5)
            }
        }
        lupa.moves++
        txt_moves.setText(lupa.moves.toString())
        if(won()) {
            Toast.makeText(this, "You win in " + lupa.moves.toString() + " moves!! OMG!!", Toast.LENGTH_LONG).show()
            switches.forEach { s -> s.isEnabled = false }
        }
    }

    fun showInfo(){
        val acercaDe = Intent(this, Info::class.java)
        startActivity(acercaDe)
    }
}
