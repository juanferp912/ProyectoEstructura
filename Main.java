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
        
        // ejecutar cálculos
        System.out.println("========== EJECUTANDO CÁLCULOS ==========");
        ejecutarTodosLosCalculos(curso);
        System.out.println();
        
        // actividades vencidas
        System.out.println("========== CONSULTA 1: ACTIVIDADES VENCIDAS ==========");
        System.out.println("Fecha actual: 2024-02-13");
        ListaCompuesta<Actividad> actividadesVencidas = ConsultasAvanzadas.obtenerActividadesVencidas(curso, "2024-02-13");
        mostrarActividadesVencidas(actividadesVencidas);
        System.out.println();
        
        System.out.println("========== CONSULTA 2: ACTIVIDADES CON ENTREGAS INCOMPLETAS ==========");
        ListaCompuesta<Actividad> actividadesIncompletas = ConsultasAvanzadas.obtenerActividadesConEntregasIncompletas(curso);
        mostrarActividadesIncompletas(actividadesIncompletas);
        System.out.println();
        
        System.out.println("========== CONSULTA 3: ESTUDIANTES CON ENTREGAS ALTAS ==========");
        ListaCompuesta<Estudiante> estudiantesEntregasAltas = ConsultasAvanzadas.obtenerEstudiantesConEntregasAltas(curso, 50.0);
        mostrarEstudiantes(estudiantesEntregasAltas);
        System.out.println();
        
        System.out.println("========== CONSULTA 4: ESTUDIANTES CON NOTAS REPETIDAS ==========");
        ListaCompuesta<Estudiante> estudiantesNotasRepetidas = ConsultasAvanzadas.obtenerEstudiantesConNotasRepetidas(curso);
        mostrarEstudiantes(estudiantesNotasRepetidas);
        System.out.println();
        
        System.out.println("========== CONSULTA 5: CÁLCULOS INCOMPLETOS ==========");
        ListaCompuesta<Calculo> calculosIncompletos = ConsultasAvanzadas.obtenerCalculosIncompletos(curso);
        mostrarCalculosList(calculosIncompletos);
        System.out.println();
        
        // generar reporte
        System.out.println("========== GENERANDO REPORTE ==========");
        generarReporte(curso);
        System.out.println();
        
        // Operaciones con ListaCompuesta
        mostrarOperaciones(curso);
        
        System.out.println("========== FIN DEL PROGRAMA ==========");
    }
    
    private static void mostrarOperaciones(Curso curso) {
        System.out.println("========== OPERACIONES CON LISTACOMPUESTA ==========");
        
        // Obtener entregas reales del curso
        System.out.println("\n ListaCompuesta con estudiantes reales del curso");
        ListaCompuesta<Entrega> lista1 = new ListaCompuesta<>();
        ListaCompuesta<Entrega> lista2 = new ListaCompuesta<>();
        System.out.println("  Se crearon dos ListaCompuesta<Entrega> vacías");
        System.out.println("  - Lista 1 tamaño: " + lista1.tamaño());
        System.out.println("  - Lista 2 tamaño: " + lista2.tamaño());
        
        // agregar elementos a lista principal 
        System.out.println("\n Agregar elementos a lista principal");
        String nombresLista1 = "";
        String nombresLista2 = "";
        
        // primeros 3 estudiantes con sus entregas
        for (int i = 0; i < curso.getEstudiantes().tamaño() && i < 3; i++) {
            Estudiante est = curso.getEstudiantes().obtener(i);
            if (est != null && est.getEntregas().tamaño() > 0) {
                Entrega ent = est.getEntregas().obtener(0);
                if (ent != null) {
                    lista1.agregar(ent);
                    if (i > 0) nombresLista1 += ", ";
                    nombresLista1 += est.getNombre();
                }
            }
        }
        
        //  primer y último estudiante
        Estudiante est1 = curso.getEstudiantes().obtener(0);
        if (est1 != null && est1.getEntregas().tamaño() > 0) {
            Entrega ent = est1.getEntregas().obtener(0);
            if (ent != null) {
                lista2.agregar(ent);
                nombresLista2 = est1.getNombre();
            }
        }
        
        Estudiante estUltimo = curso.getEstudiantes().obtener(curso.getEstudiantes().tamaño() - 1);
        if (estUltimo != null && estUltimo.getEntregas().tamaño() > 0) {
            Entrega ent = estUltimo.getEntregas().obtener(0);
            if (ent != null) {
                lista2.agregar(ent);
                nombresLista2 += ", " + estUltimo.getNombre();
            }
        }
        
        System.out.println("  - Lista 1: agregados " + lista1.tamaño() + " elementos (" + nombresLista1 + ")");
        System.out.println("    Tamaño: " + lista1.tamaño());
        System.out.println("  - Lista 2: agregados " + lista2.tamaño() + " elementos (" + nombresLista2 + ")");
        System.out.println("    Tamaño: " + lista2.tamaño());
        
        // Unión sin repetidos 
        System.out.println("\n Unión de dos listas sin repetidos");
        System.out.println("  - Lista 1: " + lista1.tamaño() + " elementos (" + nombresLista1 + ")");
        System.out.println("  - Lista 2: " + lista2.tamaño() + " elementos (" + nombresLista2 + ")");
        
        ListaCompuesta<Entrega> unionListas = lista1.union(lista2);
        System.out.println("  - UNION (sin repetidos): " + unionListas.tamaño() + " elementos");
        for (int i = 0; i < unionListas.tamaño(); i++) {
            Entrega ent = unionListas.obtener(i);
            if (ent != null) {
                System.out.println("    * " + ent.getNombre());
            }
        }
        System.out.println("  Nota: Los elementos comunes aparecen solo una vez");
        
        // Intersección
        System.out.println("\n intersección de dos listas");
        System.out.println("  - Lista 1: " + nombresLista1);
        System.out.println("  - Lista 2: " + nombresLista2);
        
        ListaCompuesta<Entrega> interseccionListas = lista1.interseccion(lista2);
        System.out.println("  - INTERSECCIÓN: " + interseccionListas.tamaño() + " elemento(s)");
        for (int i = 0; i < interseccionListas.tamaño(); i++) {
            Entrega ent = interseccionListas.obtener(i);
            if (ent != null) {
                System.out.println("    * " + ent.getNombre() + " (elemento común en ambas)");
            }
        }
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
    
    private static void ejecutarTodosLosCalculos(Curso curso) {
        for (int i = 0; i < curso.getCalculos().tamaño(); i++) {
            Calculo calc = curso.getCalculos().obtener(i);
            if (calc != null) {
                System.out.println("\nCálculo: " + calc.getNombre() + " (" + calc.getTipo() + ")");
                
                for (int j = 0; j < curso.getEstudiantes().tamaño(); j++) {
                    Estudiante est = curso.getEstudiantes().obtener(j);
                    if (est != null) {
                        Fraccion resultado = EjecutorCalculos.ejecutarCalculo(calc, est);
                        if (resultado != null) {
                            System.out.println("  " + est.getNombre() + ": " + resultado.toString() + 
                                             " (" + String.format("%.2f", resultado.aDecimal()) + ")");
                        }
                    }
                }
            }
        }
    }
    
    private static void mostrarActividadesIncompletas(ListaCompuesta<Actividad> actividades) {
        if (actividades.estaVacia()) {
            System.out.println("No hay actividades con entregas incompletas");
            return;
        }
        
        System.out.println("Actividades con entregas incompletas:");
        for (int i = 0; i < actividades.tamaño(); i++) {
            Actividad act = actividades.obtener(i);
            if (act != null) {
                System.out.println("  - " + act.getNombre());
            }
        }
    }
    
    private static void mostrarEstudiantes(ListaCompuesta<Estudiante> estudiantes) {
        if (estudiantes.estaVacia()) {
            System.out.println("No hay estudiantes que cumplan el criterio");
            return;
        }
        
        System.out.println("Estudiantes encontrados:");
        for (int i = 0; i < estudiantes.tamaño(); i++) {
            Estudiante est = estudiantes.obtener(i);
            if (est != null) {
                System.out.println("  - " + est.getNombre() + " (" + est.getCodigo() + ")");
            }
        }
    }
    
    private static void mostrarCalculosList(ListaCompuesta<Calculo> calculos) {
        if (calculos.estaVacia()) {
            System.out.println("No hay cálculos incompletos");
            return;
        }
        
        System.out.println("Cálculos incompletos:");
        for (int i = 0; i < calculos.tamaño(); i++) {
            Calculo calc = calculos.obtener(i);
            if (calc != null) {
                System.out.println("  - " + calc.getNombre() + " (" + calc.getTipo() + ")");
            }
        }
    }
    
    private static void generarReporte(Curso curso) {
        Reporte reporte = new Reporte(curso);
        
        // agregar todas las actividades al reporte
        for (int i = 0; i < curso.getActividades().tamaño(); i++) {
            Actividad act = curso.getActividades().obtener(i);
            if (act != null) {
                reporte.agregarActividad(act);
            }
        }
        
        // agregar todos los cálculos al reporte
        for (int i = 0; i < curso.getCalculos().tamaño(); i++) {
            Calculo calc = curso.getCalculos().obtener(i);
            if (calc != null) {
                reporte.agregarCalculo(calc);
            }
        }
        
        reporte.generarReporte();
    }
}
