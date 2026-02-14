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
        this.estado = "enProceso";
        this.actividades = new ListaCompuesta<>();
    }

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

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
