package mx.itesm.ggo.materias

/**
 * Created by gerry on 2/7/18.
 */

data class Materia( val nombre: String, val descripcion: String ){
    val materias = arrayOf(
        Materia("Fundamentos de programación", "TC1014\n" +
                    "Fundamentos de programación\n" +
                    "Objetivo general:\n" +
                    "Al finalizar este curso el alumno sea capaz de aplicar la lógica para generar algoritmos que den solución a problemas de ingeniería."),
        Materia("Estructura de datos", "TC1018\n" +
                    "Estructura de datos\n" +
                    "Objetivo general:\n" +
                    "Al finalizar este curso el alumno será capaz de diseñar programas usando estructuras de datos en un lenguaje de programación que den solución a problemas planteados."),
        Materia(
                "Programación avanzada",
                "TC2025\n" +
                    "Programación avanzada\n" +
                    "Objetivo general:\n" +
                    "Al finalizar el curso el alumno tendrá conocimientos avanzados sobre el desarrollo de programas en lenguaje C, su depuración e implementación para el diseño y desarrollo de aplicaciones computacionales que optimicen el aprovechamiento de los recursos del núcleo del sistema operativo. El alumno entenderá a cabalidad la administración de procesos de un sistema operativo y las técnicas de sincronización y comunicación entre ellos, así como las ventajas del desarrollo de algoritmos concurrentes y multihilos simultáneamente con la forma de implementarlos utilizando herramientas que garanticen su eficiencia."
        ),
        Materia("Evaluación y administrador de proyectos", "TI2011\n" +
                    "Evaluación y administración de proyectos\n" +
                    "Objetivo general:\n" +
                    "Al finalizar el curso el alumno será capaz de manejar el liderazgo y los recursos humanos en la administración de un proyectos en el entorno de las Tecnologías de Información, así como comunicarse en el ámbito interpersonal y grupal para coordinar los esfuerzos dirigidos al logro de proyectos exitosos.")
    );


    override fun toString(): String {
        return nombre
    }
}