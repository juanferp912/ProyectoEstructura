public class Estudiante {
    private String nombre;
    private String codigo;
    private double promedio;
    private ListaCompuesta<Entrega> entregas;

    public Estudiante(String nombre, String codigo, double promedio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.promedio = promedio;
        this.entregas = new ListaCompuesta<>();
    }
    
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    public double getPromedio() { return promedio; }
    public ListaCompuesta<Entrega> getEntregas() {return entregas;}
    
}
