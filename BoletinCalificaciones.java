public class BoletinCalificaciones {
    private Estudiante estudiante;
    private double promedio;
    private String periodo;
    private String estado;
    private ListaCompuesta<Actividad> actividades;

    public BoletinCalificaciones(Estudiante estudiante, String periodo) {
        this.estudiante = estudiante;
        this.periodo = periodo;
        this.promedio = 0;
        this.estado = "en_proceso";
        this.actividades = new ListaCompuesta<>();
    }

    // Getters
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public double getPromedio() {
        return promedio;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getEstado() {
        return estado;
    }

    public ListaCompuesta<Actividad> getActividades() {
        return actividades;
    }

    // Setters
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Operaciones TDA
    public void agregarActividad(Actividad actividad) {
        actividades.agregar(actividad);
    }

    public void calcularPromedio() {
        if (actividades.estaVacia()) {
            this.promedio = 0;
            return;
        }

        double sumaNotas = 0;
        int contadorActividades = 0;

        for (int i = 0; i < actividades.tamaño(); i++) {
            Actividad actividad = actividades.obtener(i);
            double promedioActividad = actividad.obtenerPromedioEntregas();
            if (promedioActividad > 0) {
                sumaNotas += promedioActividad;
                contadorActividades++;
            }
        }

        if (contadorActividades > 0) {
            this.promedio = sumaNotas / contadorActividades;
        } else {
            this.promedio = 0;
        }
    }

    public void finalizarPeriodo() {
        calcularPromedio();
        this.estado = "finalizado";
        estudiante.setPromedio(this.promedio);
    }

    public boolean estaAprobado(double notaMinima) {
        return promedio >= notaMinima;
    }

    public int contarActividades() {
        return actividades.tamaño();
    }

    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("========== BOLETIN DE CALIFICACIONES ==========\n");
        reporte.append("Estudiante: ").append(estudiante.getNombre()).append("\n");
        reporte.append("Código: ").append(estudiante.getCodigo()).append("\n");
        reporte.append("Período: ").append(periodo).append("\n");
        reporte.append("Promedio Final: ").append(String.format("%.2f", promedio)).append("\n");
        reporte.append("Estado: ").append(estado).append("\n");
        reporte.append("Actividades: ").append(contarActividades()).append("\n");
        reporte.append("==============================================");
        return reporte.toString();
    }
}
