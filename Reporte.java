public class Reporte {
    private Curso curso;
    private ListaCompuesta<Actividad> actividadesSeleccionadas;
    private ListaCompuesta<Calculo> calculosSeleccionados;

    public Reporte(Curso curso) {
        this.curso = curso;
        this.actividadesSeleccionadas = new ListaCompuesta<>();
        this.calculosSeleccionados = new ListaCompuesta<>();
    }

    public void agregarActividad(Actividad actividad) {
        actividadesSeleccionadas.agregar(actividad);
    }

    public void agregarCalculo(Calculo calculo) {
        calculosSeleccionados.agregar(calculo);
    }

    public void generarReporte() {
        System.out.println("\n========== REPORTE DE CALIFICACIONES ==========");
        System.out.println("Curso: " + curso.getNombre());
        System.out.println();

        System.out.println("--- ACTIVIDADES EN EL REPORTE ---");
        mostrarActividadesIncluidas();
        System.out.println();

        System.out.println("--- CALCULOS EN EL REPORTE ---");
        mostrarCalculosIncluidos();
        System.out.println();

        System.out.println("--- CALIFICACIONES POR ESTUDIANTE ---");
        mostrarCalificacionesEstudiantes();
    }

    private void mostrarActividadesIncluidas() {
        if (actividadesSeleccionadas.estaVacia()) {
            System.out.println("No hay actividades seleccionadas");
            return;
        }
        
        for (int i = 0; i < actividadesSeleccionadas.tamaño(); i++) {
            Actividad act = actividadesSeleccionadas.obtener(i);
            if (act != null) {
                System.out.println("  - " + act.getNombre());
            }
        }
    }

    private void mostrarCalculosIncluidos() {
        if (calculosSeleccionados.estaVacia()) {
            System.out.println("No hay cálculos seleccionados");
            return;
        }
        
        for (int i = 0; i < calculosSeleccionados.tamaño(); i++) {
            Calculo calc = calculosSeleccionados.obtener(i);
            if (calc != null) {
                System.out.println("  - " + calc.getNombre() + " (" + calc.getTipo() + ")");
            }
        }
    }

    private void mostrarCalificacionesEstudiantes() {
        for (int i = 0; i < curso.getEstudiantes().tamaño(); i++) {
            Estudiante est = curso.getEstudiantes().obtener(i);
            if (est != null) {
                System.out.println("\n  Estudiante: " + est.getNombre() + " (" + est.getCodigo() + ")");
                
                if (!actividadesSeleccionadas.estaVacia()) {
                    System.out.println("    Calificaciones en actividades:");
                    mostrarCalificacionesActividades(est);
                }
                
                if (!calculosSeleccionados.estaVacia()) {
                    System.out.println("    Resultados de cálculos:");
                    mostrarCalificacionesCalculos(est);
                }
            }
        }
    }

    private void mostrarCalificacionesActividades(Estudiante est) {
        for (int i = 0; i < actividadesSeleccionadas.tamaño(); i++) {
            Actividad act = actividadesSeleccionadas.obtener(i);
            if (act != null) {
                Fraccion nota = obtenerNotaEstudianteEnActividad(est, act);
                if (nota != null) {
                    System.out.println("      " + act.getNombre() + ": " + nota.toString());
                } else {
                    System.out.println("      " + act.getNombre() + ": Sin calificación");
                }
            }
        }
    }

    private void mostrarCalificacionesCalculos(Estudiante est) {
        for (int i = 0; i < calculosSeleccionados.tamaño(); i++) {
            Calculo calc = calculosSeleccionados.obtener(i);
            if (calc != null) {
                Fraccion resultado = EjecutorCalculos.ejecutarCalculo(calc, est);
                if (resultado != null) {
                    System.out.println("      " + calc.getNombre() + ": " + resultado.toString());
                }
            }
        }
    }

    private Fraccion obtenerNotaEstudianteEnActividad(Estudiante est, Actividad act) {
        for (int i = 0; i < est.getEntregas().tamaño(); i++) {
            Entrega ent = est.getEntregas().obtener(i);
            if (ent != null && ent.getNombre().equals(act.getNombre())) {
                return ent.getNota();
            }
        }
        return null;
    }
}
