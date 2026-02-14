import java.util.Comparator;

public class ConsultasCurso {

    public static ListaCompuesta<Actividad> obtenerActividadesVencidas(Curso curso, String fechaActual) {
        return curso.getActividades().filtrar(new Comparator<Actividad>() {
            @Override
            public int compare(Actividad a1, Actividad a2) {
                if (estaVencida(a1.getFechaLimite(), fechaActual)) {
                    return 0;
                }
                return -1;
            }
        });
    }

    private static boolean estaVencida(String fechaLimite, String fechaActual) {
        int resultadoComparacion = compararFechas(fechaLimite, fechaActual);
        return resultadoComparacion < 0;
    }

    private static int compararFechas(String fecha1, String fecha2) {
        return fecha1.compareTo(fecha2);
    }

    public static ListaCompuesta<Estudiante> obtenerEstudiantesConNotaRepetida(Curso curso) {
        ListaCompuesta<Estudiante> estudiantes = curso.getEstudiantes();
        ListaCompuesta<Estudiante> resultado = new ListaCompuesta<>();
        
        for (int i = 0; i < estudiantes.tamaño(); i++) {
            Estudiante est = estudiantes.obtener(i);
            if (est != null && tieneNotaRepetida(est)) {
                resultado.agregar(est);
            }
        }
        
        return resultado;
    }

    private static boolean tieneNotaRepetida(Estudiante estudiante) {
        ListaCompuesta<Entrega> entregas = estudiante.getEntregas();
        
        for (int i = 0; i < entregas.tamaño(); i++) {
            Entrega e1 = entregas.obtener(i);
            if (e1 != null) {
                for (int j = i + 1; j < entregas.tamaño(); j++) {
                    Entrega e2 = entregas.obtener(j);
                    if (e2 != null && e1.getNota().equals(e2.getNota())) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}
