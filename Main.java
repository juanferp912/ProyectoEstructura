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
        mostrarOperaciones();
        
        System.out.println("========== FIN DEL PROGRAMA ==========");
    }
    
    private static void mostrarOperaciones() {
        System.out.println("========== OPERACIONES CON LISTACOMPUESTA ==========");
        
        // ListaCompuesta
        System.out.println("\n ListaCompuesta");
        ListaCompuesta<Entrega> lista1 = new ListaCompuesta<>();
        ListaCompuesta<Entrega> lista2 = new ListaCompuesta<>();
        System.out.println("  Se crearon dos ListaCompuesta<Entrega> vacías");
        System.out.println("  - Lista 1 tamaño: " + lista1.tamaño());
        System.out.println("  - Lista 2 tamaño: " + lista2.tamaño());
        
        // agregar elementos a lista principal
        System.out.println("\n Agregar elementos a lista principal");
        Entrega e1 = new Entrega("Entrega Juan", "Completada", new Fraccion(8, 10), "2024-02-14");
        Entrega e2 = new Entrega("Entrega María", "Completada", new Fraccion(9, 10), "2024-02-15");
        Entrega e3 = new Entrega("Entrega Pedro", "Pendiente", new Fraccion(0, 10), "2024-02-16");
        Entrega e4 = new Entrega("Entrega Ana", "Completada", new Fraccion(10, 10), "2024-02-17");
        
        lista1.agregar(e1);
        lista1.agregar(e2);
        lista1.agregar(e3);
        
        lista2.agregar(e1);
        lista2.agregar(e4);
        
        System.out.println("  - Lista 1: agregados 3 elementos (Juan, María, Pedro)");
        System.out.println("    Tamaño: " + lista1.tamaño());
        System.out.println("  - Lista 2: agregados 2 elementos (Juan, Ana)");
        System.out.println("    Tamaño: " + lista2.tamaño());
        
        //agregar elementos a lista secundaria 
        System.out.println("\n Agregar elementos a lista secundaria");
        Actividad actDemo = new Actividad("Taller Demostrativo", "Demo para profesora", "2024-03-01", 10.0);
        Entrega entDemo1 = new Entrega("Entrega estudiante 1", "Completa", new Fraccion(7, 10), "2024-02-28");
        Entrega entDemo2 = new Entrega("Entrega estudiante 2", "Incompleta", new Fraccion(5, 10), "2024-02-28");
        
        actDemo.getEntregas().agregar(entDemo1);
        actDemo.getEntregas().agregar(entDemo2);
        
        System.out.println("  - Actividad: " + actDemo.getNombre());
        System.out.println("  - Entregas agregadas a actividad: " + actDemo.getEntregas().tamaño());
        for (int i = 0; i < actDemo.getEntregas().tamaño(); i++) {
            Entrega ent = actDemo.getEntregas().obtener(i);
            if (ent != null) {
                System.out.println("    * " + ent.getNombre() + " - Estado: " + ent.getEstado());
            }
        }
        
        // Filtrar por criterio en listas secundarias
        System.out.println("\n Filtrar por criterio en listas secundarias");
        ListaCompuesta<Actividad> actividadesConIncompletas = new ListaCompuesta<>();
        actividadesConIncompletas.agregar(actDemo);
        
        System.out.println("  - Criterio: actividades con entregas incompletas");
        System.out.println("  - Actividad: " + actDemo.getNombre());
        int entregasIncompletas = 0;
        for (int i = 0; i < actDemo.getEntregas().tamaño(); i++) {
            Entrega ent = actDemo.getEntregas().obtener(i);
            if (ent != null && ent.getEstado().equals("Incompleta")) {
                entregasIncompletas++;
            }
        }
        System.out.println("  - Entregas incompletas encontradas: " + entregasIncompletas);
        
        // filtrar por criterio en lista principal
        System.out.println("\n✓ FASE 5: Filtrar por criterio en lista principal");
        ListaCompuesta<Entrega> listaFiltrada = new ListaCompuesta<>();
        System.out.println("  - Criterio: entregas con estado 'Completada'");
        System.out.println("  - Lista 1 original: " + lista1.tamaño() + " elementos");
        
        for (int i = 0; i < lista1.tamaño(); i++) {
            Entrega ent = lista1.obtener(i);
            if (ent != null && ent.getEstado().equals("Completada")) {
                listaFiltrada.agregar(ent);
            }
        }
        System.out.println("  - Entregas con estado 'Completada': " + listaFiltrada.tamaño());
        for (int i = 0; i < listaFiltrada.tamaño(); i++) {
            Entrega ent = listaFiltrada.obtener(i);
            if (ent != null) {
                System.out.println("    * " + ent.getNombre());
            }
        }
        
        // Unión sin repetidos 
        System.out.println("\n Unión de dos listas sin repetidos");
        System.out.println("  - Lista 1: " + lista1.tamaño() + " elementos (Juan, María, Pedro)");
        System.out.println("  - Lista 2: " + lista2.tamaño() + " elementos (Juan, Ana)");
        
        ListaCompuesta<Entrega> unionListas = lista1.union(lista2);
        System.out.println("  - UNION (sin repetidos): " + unionListas.tamaño() + " elementos");
        for (int i = 0; i < unionListas.tamaño(); i++) {
            Entrega ent = unionListas.obtener(i);
            if (ent != null) {
                System.out.println("    * " + ent.getNombre());
            }
        }
        System.out.println("  Nota: 'Juan' aparece solo una vez aunque estaba en ambas listas");
        
        // Intersección
        System.out.println("\n intersección de dos listas");
        System.out.println("  - Lista 1: Juan, María, Pedro");
        System.out.println("  - Lista 2: Juan, Ana");
        
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
