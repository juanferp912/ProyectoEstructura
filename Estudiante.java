public class Estudiante {
    private String nombre;
    private String codigo;
    private double promedio;
    private String correo;
    private ListaCompuesta<Entrega> entregas;
    private BoletinCalificaciones boletin;

    public Estudiante(String nombre, String codigo, double promedio, String correo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.promedio = promedio;
        this.correo = correo;
        this.entregas = new ListaCompuesta<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPromedio() {
        return promedio;
    }

    public String getCorreo() {
        return correo;
    }

    public ListaCompuesta<Entrega> getEntregas() {
        return entregas;
    }

    public BoletinCalificaciones getBoletin() {
        return boletin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void asignarBoletin(BoletinCalificaciones boletin) {
        this.boletin = boletin;
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

    public void actualizarPromedio(double nuevoPromedio) {
        this.promedio = nuevoPromedio;
    }
}
