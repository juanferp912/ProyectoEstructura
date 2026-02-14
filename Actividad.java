public class Actividad {
    private String nombre;
    private String descripcion;
    private String fechaLimite;
    private double puntajeMaximo;
    private ListaCompuesta<Entrega> entregas;

    public Actividad(String nombre, String descripcion, String fechaLimite, double puntajeMaximo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.puntajeMaximo = puntajeMaximo;
        this.entregas = new ListaCompuesta<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public double getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public ListaCompuesta<Entrega> getEntregas() {
        return entregas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public void setPuntajeMaximo(double puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }

    public void agregarEntrega(Entrega entrega) {
        entregas.agregar(entrega);
    }

    public double obtenerPromedioEntregas() {
        if (entregas.estaVacia()) {
            return 0;
        }
        double sumaNotas = 0;
        for (int i = 0; i < entregas.tamaño(); i++) {
            sumaNotas += entregas.obtener(i).getNota();
        }
        return sumaNotas / entregas.tamaño();
    }

    public int contarEntregas() {
        return entregas.tamaño();
    }
}
