package mx.itesm.ggo.juegoppt

/**
 * Created by gerry on 1/17/18.
 */
class PiedraPapelTijeras{
    var puntosCompu: Int = 0
    var puntosJugador: Int = 0

    fun reset(){
        puntosCompu = 0
        puntosJugador = 0
    }

    fun juegoAzar(): Jugada {
        return Jugada.values()[ (Math.random()*3).toInt() ]
    }

    fun jugar(jJugador: Jugada, jCompu: Jugada ):Resultado {
        if( jJugador == jCompu ) return Resultado.EMPATE

        if(jJugador == Jugada.PIEDRA && jCompu == Jugada.TIJERAS){
            puntosJugador++
            return Resultado.GANAS
        }

        if(jJugador == Jugada.PAPEL && jCompu == Jugada.PIEDRA){
            puntosJugador++
            return Resultado.GANAS
        }

        if(jJugador == Jugada.TIJERAS && jCompu == Jugada.PAPEL){
            puntosJugador++
            return Resultado.GANAS
        }

        puntosCompu++
        return Resultado.PIERDES
    }

}

enum class Resultado {
    GANAS,
    PIERDES,
    EMPATE
}

enum class Jugada {
    PIEDRA,
    PAPEL,
    TIJERAS
}
