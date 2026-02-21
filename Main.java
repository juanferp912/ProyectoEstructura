public class Main {
    public static void main(String[] args) {
        System.out.println("========== CARGANDO DATOS ==========");
        
        // Cargar Semestre 1
        Curso cursoS1 = CargadorDatos.cargarCurso(
            "Estructura de Datos - Semestre 1",
            "CARP912",
            3.0,
            "Dr. Xavier Barriga",
            4,
            "estudiantes_s1.csv",
            "actividades_s1.csv",
            "entregas_s1.csv",
            "calculos_s1.csv"
        );
        
        System.out.println("Semestre 1 cargado: " + cursoS1.getNombre());
        System.out.println("Estudiantes: " + cursoS1.getEstudiantes().tamaño());
        System.out.println("Actividades: " + cursoS1.getActividades().tamaño());
        System.out.println("Cálculos: " + cursoS1.getCalculos().tamaño());
        mostrarCalculos(cursoS1);
        System.out.println();
        
        // Ejecutar cálculos del semestre 1
        System.out.println("========== EJECUTANDO CÁLCULOS ==========");
        ejecutarTodosLosCalculos(cursoS1);
        System.out.println();
        
        // Consultas del semestre 1
        System.out.println("========== ACTIVIDADES VENCIDAS ========");
        System.out.println("Fecha actual: 2024-02-13");
        ListaCompuesta<Actividad> actividadesVencidas = ConsultasAvanzadas.obtenerActividadesVencidas(cursoS1, "2024-02-13");
        mostrarActividadesVencidas(actividadesVencidas);
        System.out.println();
        
        System.out.println("====== ACTIVIDADES CON ENTREGAS INCOMPLETAS ======");
        ListaCompuesta<Actividad> actividadesIncompletas = ConsultasAvanzadas.obtenerActividadesConEntregasIncompletas(cursoS1);
        mostrarActividadesIncompletas(actividadesIncompletas);
        System.out.println("NOTA: Gustavo Mejia (CARP06) no entregó Proyecto");
        System.out.println("NOTA: Santino Gonzalez (CARP08) no entregó Examen1");
        System.out.println();
        
        System.out.println("======= ESTUDIANTES CON ENTREGAS ALTAS =======");
        ListaCompuesta<Estudiante> estudiantesEntregasAltas = ConsultasAvanzadas.obtenerEstudiantesConEntregasAltas(cursoS1, 50.0);
        mostrarEstudiantes(estudiantesEntregasAltas);
        System.out.println();
        
        System.out.println("========== ESTUDIANTES CON NOTAS REPETIDAS ==========");
        ListaCompuesta<Estudiante> estudiantesNotasRepetidas = ConsultasAvanzadas.obtenerEstudiantesConNotasRepetidas(cursoS1);
        mostrarEstudiantes(estudiantesNotasRepetidas);
        System.out.println();
        
        System.out.println("========== CÁLCULOS INCOMPLETOS ========");
        ListaCompuesta<Calculo> calculosIncompletos = ConsultasAvanzadas.obtenerCalculosIncompletos(cursoS1);
        mostrarCalculosList(calculosIncompletos);
        System.out.println();
        
        // Generar reporte del semestre 1
        System.out.println("========= GENERANDO REPORTE ========");
        generarReporte(cursoS1);
        System.out.println();
        
        // Operaciones con ListaCompuesta
        mostrarOperaciones(cursoS1);
        System.out.println();
        
        // Cargar Semestre 2
        Curso cursoS2 = CargadorDatos.cargarCurso(
            "Estructura de Datos - Semestre 2",
            "CARP912",
            3.0,
            "Dr. Xavier Barriga",
            4,
            "estudiantes_s2.csv",
            "actividades_s2.csv",
            "entregas_s2.csv",
            "calculos_s2.csv"
        );
        
        // Ejecutar cálculos del semestre 2
        System.out.println("Curso cargado: " + cursoS2.getNombre());
        System.out.println("Estudiantes: " + cursoS2.getEstudiantes().tamaño());
        System.out.println();
        
        System.out.println("======== EJECUTANDO CÁLCULOS SEMESTRE 2 =========");
        ejecutarTodosLosCalculos(cursoS2);
        System.out.println();
        
        // Generar reporte del semestre 2
        System.out.println("======== GENERANDO REPORTE SEMESTRE 2 ========");
        generarReporte(cursoS2);
        System.out.println();
        
        // Reporte combinado de ambos semestres
        System.out.println("========= REPORTE COMBINADO - AMBOS SEMESTRES =======");
        generarReporteCombinado(cursoS1, cursoS2);
        System.out.println();
    
        System.out.println("========= FIN DEL PROGRAMA =======");
    }
    
    private static void generarReporteCombinado(Curso cursoS1, Curso cursoS2) {
        System.out.println();
        
        // Iterar sobre estudiantes
        for (int i = 0; i < cursoS1.getEstudiantes().tamaño(); i++) {
            Estudiante estS1 = cursoS1.getEstudiantes().obtener(i);
            Estudiante estS2 = cursoS2.getEstudiantes().obtener(i);
            
            if (estS1 != null && estS2 != null) {
                System.out.println("\n  Estudiante: " + estS1.getNombre() + " (" + estS1.getCodigo() + ")");
                
                // Obtener promedios de ambos semestres
                Fraccion promedioS1 = null;
                Fraccion promedioS2 = null;
                
                for (int j = 0; j < cursoS1.getCalculos().tamaño(); j++) {
                    Calculo calc = cursoS1.getCalculos().obtener(j);
                    if (calc != null && calc.getTipo().equals("promedio")) {
                        promedioS1 = EjecutorCalculos.ejecutarCalculo(calc, estS1);
                        break;
                    }
                }
                
                for (int j = 0; j < cursoS2.getCalculos().tamaño(); j++) {
                    Calculo calc = cursoS2.getCalculos().obtener(j);
                    if (calc != null && calc.getTipo().equals("promedio")) {
                        promedioS2 = EjecutorCalculos.ejecutarCalculo(calc, estS2);
                        break;
                    }
                }
                
                if (promedioS1 != null && promedioS2 != null) {
                    System.out.println("    Semestre 1: " + promedioS1.toString());
                    System.out.println("    Semestre 2: " + promedioS2.toString());
                    
                    // Calcular suma ponderada final (promedio de ambos semestres)
                    double promedioFinal = (promedioS1.aDecimal() + promedioS2.aDecimal()) / 2;
                    int numerador = (int) Math.round(promedioFinal * 10);
                    Fraccion sumaPonderadaFinal = new Fraccion(numerador, 10, false);
                    
                    System.out.println("    Suma Ponderada Final: " + sumaPonderadaFinal.toString());
                }
            }
        }
    }
    
    private static void mostrarOperaciones(Curso curso) {
        System.out.println("====== OPERACIONES CON LISTACOMPUESTA ========");
        
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
