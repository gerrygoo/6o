package mx.itesm.ggo.materias


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 */
class MateriaDetalle : Fragment() {

    var ID: Long = 0

    companion object {
        fun newInstance(): MateriaDetalle {
            return MateriaDetalle()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_materia_detalle, container, false)
    }

}// Required empty public constructor
