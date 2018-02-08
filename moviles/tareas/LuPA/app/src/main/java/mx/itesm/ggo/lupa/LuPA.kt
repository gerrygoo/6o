package mx.itesm.ggo.lupa

/**
 * Created by gerry on 1/21/18.
 */
class LuPA(n: Int){
    var toggles = Array<Boolean>(n, { _ -> Math.random() > 0.5 })
    var moves = 0;
    fun reset(){
        moves = 0;
        for(i in 0 .. (toggles.size - 1)) toggles[i] = Math.random() > 0.5
    }

}
