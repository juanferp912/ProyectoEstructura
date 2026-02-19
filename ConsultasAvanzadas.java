public class ConsultasAvanzadas {

    // ACTIVIDADES - su fecha de entrega límite ya feneció
    public static ListaCompuesta<Actividad> obtenerActividadesVencidas(Curso curso, String fechaActual) {
        ListaCompuesta<Actividad> resultado = new ListaCompuesta<>();
        for (int i = 0; i < curso.getActividades().tamaño(); i++) {
            Actividad act = curso.getActividades().obtener(i);
            if (act != null && estaVencida(act.getFechaLimite(), fechaActual)) {
                resultado.agregar(act);
            }
        }
        return resultado;
    }

    // ACTIVIDADES - las entregas estén incompletas
    public static ListaCompuesta<Actividad> obtenerActividadesConEntregasIncompletas(Curso curso) {
        ListaCompuesta<Actividad> resultado = new ListaCompuesta<>();
        for (int i = 0; i < curso.getActividades().tamaño(); i++) {
            Actividad act = curso.getActividades().obtener(i);
            if (act != null && act.getEntregas().tamaño() < curso.getEstudiantes().tamaño()) {
                resultado.agregar(act);
            }
        }
        return resultado;
    }

    // ACTIVIDADES - las calificaciones de las entregas son menores a un valor dado
    public static ListaCompuesta<Actividad> obtenerActividadesConCalificacionesBajas(Curso curso, double valorMinimo) {
        ListaCompuesta<Actividad> resultado = new ListaCompuesta<>();
        for (int i = 0; i < curso.getActividades().tamaño(); i++) {
            Actividad act = curso.getActividades().obtener(i);
            if (act != null && tieneEntregasBajas(act, valorMinimo)) {
                resultado.agregar(act);
            }
        }
        return resultado;
    }

    // ENTREGAS - Enviadas luego de una cierta fecha y que aún no han recibido calificación
    public static ListaCompuesta<Entrega> obtenerEntregasSinCalificarAntiguedad(Curso curso, String fechaLimite) {
        ListaCompuesta<Entrega> resultado = new ListaCompuesta<>();
        for (int i = 0; i < curso.getEstudiantes().tamaño(); i++) {
            Estudiante est = curso.getEstudiantes().obtener(i);
            if (est != null) {
                for (int j = 0; j < est.getEntregas().tamaño(); j++) {
                    Entrega ent = est.getEntregas().obtener(j);
                    if (ent != null && ent.getEstado().equals("pendiente") && 
                        ent.getFechaEntrega().compareTo(fechaLimite) > 0) {
                        resultado.agregar(ent);
                    }
                }
            }
        }
        return resultado;
    }

    // ESTUDIANTES - su porcentaje de entregas a actividades es mayor a un porcentaje dado
    public static ListaCompuesta<Estudiante> obtenerEstudiantesConEntregasAltas(Curso curso, double porcentajeMinimo) {
        ListaCompuesta<Estudiante> resultado = new ListaCompuesta<>();
        for (int i = 0; i < curso.getEstudiantes().tamaño(); i++) {
            Estudiante est = curso.getEstudiantes().obtener(i);
            if (est != null) {
                double porcentaje = (est.getEntregas().tamaño() * 100.0) / curso.getActividades().tamaño();
                if (porcentaje >= porcentajeMinimo) {
                    resultado.agregar(est);
                }
            }
        }
        return resultado;
    }

    // ESTUDIANTES - no han respondido aún actividades enviadas y ya expiradas
    public static ListaCompuesta<Estudiante> obtenerEstudiantesConActividadesPendientes(Curso curso, String fechaActual) {
        ListaCompuesta<Estudiante> resultado = new ListaCompuesta<>();
        for (int i = 0; i < curso.getEstudiantes().tamaño(); i++) {
            Estudiante est = curso.getEstudiantes().obtener(i);
            if (est != null && tieneActividadesPendientes(curso, est, fechaActual)) {
                resultado.agregar(est);
            }
        }
        return resultado;
    }

    // ESTUDIANTES - tienen misma nota en dos actividades diferentes
    public static ListaCompuesta<Estudiante> obtenerEstudiantesConNotasRepetidas(Curso curso) {
        ListaCompuesta<Estudiante> resultado = new ListaCompuesta<>();
        for (int i = 0; i < curso.getEstudiantes().tamaño(); i++) {
            Estudiante est = curso.getEstudiantes().obtener(i);
            if (est != null && tieneNotasRepetidas(est)) {
                resultado.agregar(est);
            }
        }
        return resultado;
    }

    // CÁLCULOS - no se pueden ejecutar porque faltan calificaciones
    public static ListaCompuesta<Calculo> obtenerCalculosIncompletos(Curso curso) {
        ListaCompuesta<Calculo> resultado = new ListaCompuesta<>();
        for (int i = 0; i < curso.getCalculos().tamaño(); i++) {
            Calculo calc = curso.getCalculos().obtener(i);
            if (calc != null && !tieneTodasLasCalificaciones(curso, calc)) {
                resultado.agregar(calc);
            }
        }
        return resultado;
    }

    // CÁLCULOS - involucra una actividad dada
    public static ListaCompuesta<Calculo> obtenerCalculosPorActividad(Curso curso, String nombreActividad) {
        ListaCompuesta<Calculo> resultado = new ListaCompuesta<>();
        for (int i = 0; i < curso.getCalculos().tamaño(); i++) {
            Calculo calc = curso.getCalculos().obtener(i);
            if (calc != null && involucraActividad(calc, nombreActividad)) {
                resultado.agregar(calc);
            }
        }
        return resultado;
    }

    // Métodos auxiliares
    private static boolean estaVencida(String fechaLimite, String fechaActual) {
        return fechaLimite.compareTo(fechaActual) < 0;
    }

    private static boolean tieneEntregasBajas(Actividad act, double valorMinimo) {
        for (int i = 0; i < act.getEntregas().tamaño(); i++) {
            Entrega ent = act.getEntregas().obtener(i);
            if (ent != null && ent.getNota().aDecimal() < valorMinimo) {
                return true;
            }
        }
        return false;
    }

    private static boolean tieneActividadesPendientes(Curso curso, Estudiante est, String fechaActual) {
        for (int i = 0; i < curso.getActividades().tamaño(); i++) {
            Actividad act = curso.getActividades().obtener(i);
            if (act != null && estaVencida(act.getFechaLimite(), fechaActual)) {
                if (!estudianteCompletoActividad(est, act)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean estudianteCompletoActividad(Estudiante est, Actividad act) {
        for (int i = 0; i < est.getEntregas().tamaño(); i++) {
            Entrega ent = est.getEntregas().obtener(i);
            if (ent != null && ent.getNombre().equals(act.getNombre())) {
                return true;
            }
        }
        return false;
    }

    private static boolean tieneNotasRepetidas(Estudiante estudiante) {
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

    private static boolean tieneTodasLasCalificaciones(Curso curso, Calculo calc) {
        for (int i = 0; i < calc.getActividadesNombres().tamaño(); i++) {
            String nombreAct = calc.getActividadesNombres().obtener(i);
            if (nombreAct != null) {
                boolean tieneCalificaciones = false;
                for (int j = 0; j < curso.getActividades().tamaño(); j++) {
                    Actividad act = curso.getActividades().obtener(j);
                    if (act != null && act.getNombre().equals(nombreAct) && 
                        act.getEntregas().tamaño() > 0) {
                        tieneCalificaciones = true;
                        break;
                    }
                }
                if (!tieneCalificaciones) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean involucraActividad(Calculo calc, String nombreActividad) {
        for (int i = 0; i < calc.getActividadesNombres().tamaño(); i++) {
            String nombre = calc.getActividadesNombres().obtener(i);
            if (nombre != null && nombre.equals(nombreActividad)) {
                return true;
            }
        }
        return false;
    }
}
