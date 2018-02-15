package mx.itesm.ins.materias;

/**
 * Created by ianne on 7/02/2018.
 */

public class Materia {
    private String nombre;
    private String descripcion;

    public static final Materia[] materias = {
            new Materia("Fundamentos de programación", "TC1014\n" +
                    "Fundamentos de programación\n" +
                    "Objetivo general:\n" +
                    "Al finalizar este curso el alumno sea capaz de aplicar la lógica para generar algoritmos que den solución a problemas de ingeniería."),
            new Materia("Estructura de datos", "TC1018\n" +
                    "Estructura de datos\n" +
                    "Objetivo general:\n" +
                    "Al finalizar este curso el alumno será capaz de diseñar programas usando estructuras de datos en un lenguaje de programación que den solución a problemas planteados."),
            new Materia("Programación avanzada", "TC2025\n" +
                    "Programación avanzada\n" +
                    "Objetivo general:\n" +
                    "Al finalizar el curso el alumno tendrá conocimientos avanzados sobre el desarrollo de programas en lenguaje C, su depuración e implementación para el diseño y desarrollo de aplicaciones computacionales que optimicen el aprovechamiento de los recursos del núcleo del sistema operativo. El alumno entenderá a cabalidad la administración de procesos de un sistema operativo y las técnicas de sincronización y comunicación entre ellos, así como las ventajas del desarrollo de algoritmos concurrentes y multihilos simultáneamente con la forma de implementarlos utilizando herramientas que garanticen su eficiencia."),
            new Materia("Evaluación y administrador de proyectos", "TI2011\n" +
                    "Evaluación y administración de proyectos\n" +
                    "Objetivo general:\n" +
                    "Al finalizar el curso el alumno será capaz de manejar el liderazgo y los recursos humanos en la administración de un proyectos en el entorno de las Tecnologías de Información, así como comunicarse en el ámbito interpersonal y grupal para coordinar los esfuerzos dirigidos al logro de proyectos exitosos.")
    };

    public Materia(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
