public class Main {
    public static void main(String[] args) {
        System.out.println("========== CARGANDO DATOS ==========");
        
        Curso curso = CargadorDatos.cargarCurso(
            "Estructura de Datos",
            "EST001",
            3.0,
            "Dr. Xavier Barriga",
            4,
            "estudiantes.csv",
            "actividades.csv",
            "entregas.csv",
            "calculos.csv"
        );
        
        System.out.println("Curso cargado: " + curso.getNombre());
        System.out.println("Estudiantes: " + curso.getEstudiantes().tamaño());
        System.out.println("Actividades: " + curso.getActividades().tamaño());
        System.out.println("Cálculos: " + curso.getCalculos().tamaño());
        mostrarCalculos(curso);
        System.out.println();
        
        // actividades vencidas
        System.out.println("========== CONSULTA 1: ACTIVIDADES VENCIDAS ==========");
        System.out.println("Fecha actual: 2024-02-13");
        ListaCompuesta<Actividad> actividadesVencidas = ConsultasCurso.obtenerActividadesVencidas(curso, "2024-02-13");
        
        mostrarActividadesVencidas(actividadesVencidas);
        System.out.println();
        
        // estudiantes con misma nota en diferentes actividades
        System.out.println("========== CONSULTA 2: ESTUDIANTES CON NOTA REPETIDA ==========");
        ListaCompuesta<Estudiante> estudiantesConNotaRepetida = ConsultasCurso.obtenerEstudiantesConNotaRepetida(curso);
        
        mostrarEstudiantesConNotaRepetida(curso, estudiantesConNotaRepetida);
        System.out.println();
        
        // procesar entregas en orden FIFO
        System.out.println("========== CONSULTA 3: PROCESAR ENTREGAS EN ORDEN FIFO ==========");
        procesarEntregasEnCola(curso);
        System.out.println();
        
        System.out.println("========== FIN DEL PROGRAMA ==========");
    }
    
    private static void mostrarActividadesVencidas(ListaCompuesta<Actividad> actividades) {
        if (actividades.estaVacia()) {
            System.out.println("No hay actividades vencidas.");
        } else {
            System.out.println("Actividades vencidas encontradas: " + actividades.tamaño());
            mostrarActividadesRecursivo(actividades, 0);
        }
    }
    
    private static void mostrarActividadesRecursivo(ListaCompuesta<Actividad> actividades, int indice) {
        if (indice >= actividades.tamaño()) {
            return;
        }
        
        Actividad act = actividades.obtener(indice);
        if (act != null) {
            System.out.println("- " + act.getNombre() + " (Fecha límite: " + act.getFechaLimite() + ")");
        }
        
        mostrarActividadesRecursivo(actividades, indice + 1);
    }
    
    private static void mostrarEstudiantesConNotaRepetida(Curso curso, ListaCompuesta<Estudiante> estudiantes) {
        if (estudiantes.estaVacia()) {
            System.out.println("No hay estudiantes con notas repetidas.");
        } else {
            System.out.println("Estudiantes con notas repetidas: " + estudiantes.tamaño());
            mostrarEstudiantesRecursivo(curso, estudiantes, 0);
        }
    }
    
    private static void mostrarEstudiantesRecursivo(Curso curso, ListaCompuesta<Estudiante> estudiantes, int indice) {
        if (indice >= estudiantes.tamaño()) {
            return;
        }
        
        Estudiante est = estudiantes.obtener(indice);
        if (est != null) {
            System.out.println("- " + est.getNombre() + " (" + est.getCodigo() + ")");
            mostrarEntregasEstudiante(est.getEntregas());
        }
        
        mostrarEstudiantesRecursivo(curso, estudiantes, indice + 1);
    }
    
    private static void mostrarEntregasEstudiante(ListaCompuesta<Entrega> entregas) {
        System.out.println("  Entregas del estudiante:");
        mostrarEntregasRecursivo(entregas, 0);
    }
    
    private static void mostrarEntregasRecursivo(ListaCompuesta<Entrega> entregas, int indice) {
        if (indice >= entregas.tamaño()) {
            return;
        }
        
        Entrega ent = entregas.obtener(indice);
        if (ent != null) {
            System.out.println("    * " + ent.getNombre() + ": " + ent.getNota().toString());
        }
        
        mostrarEntregasRecursivo(entregas, indice + 1);
    }
    
    private static void procesarEntregasEnCola(Curso curso) {
        Cola<Entrega> colaEntregas = new Cola<>();
        
        // llenar la cola con todas las entregas
        for (int i = 0; i < curso.getEstudiantes().tamaño(); i++) {
            Estudiante est = curso.getEstudiantes().obtener(i);
            if (est != null) {
                for (int j = 0; j < est.getEntregas().tamaño(); j++) {
                    Entrega ent = est.getEntregas().obtener(j);
                    if (ent != null) {
                        colaEntregas.encolar(ent);
                    }
                }
            }
        }
        
        // procesar entregas en orden FIFO
        System.out.println("Procesando " + colaEntregas.tamaño() + " entregas en orden FIFO:");
        while (!colaEntregas.estaVacia()) {
            Entrega ent = colaEntregas.desencolar();
            if (ent != null) {
                System.out.println("  Procesando: " + ent.getNombre() + " - Nota: " + ent.getNota().toString());
            }
        }
    }
    
    private static void mostrarCalculos(Curso curso) {
        if (curso.getCalculos().estaVacia()) {
            System.out.println("No hay cálculos cargados");
            return;
        }
        
        System.out.println("Cálculos disponibles:");
        for (int i = 0; i < curso.getCalculos().tamaño(); i++) {
            Calculo calc = curso.getCalculos().obtener(i);
            if (calc != null) {
                System.out.println("  - " + calc.getNombre() + " (Tipo: " + calc.getTipo() + ")");
                System.out.println("    Actividades: ");
                for (int j = 0; j < calc.getActividadesNombres().tamaño(); j++) {
                    String act = calc.getActividadesNombres().obtener(j);
                    if (act != null) {
                        System.out.println("      * " + act);
                    }
                }
            }
        }
    }
}
