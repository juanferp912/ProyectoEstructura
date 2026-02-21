public class Calculo {
    private String nombre;
    private String tipo;
    private ListaCompuesta<String> actividadesNombres;
    private ListaCompuesta<Double> pesos; 

    public Calculo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.actividadesNombres = new ListaCompuesta<>();
        this.pesos = new ListaCompuesta<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public ListaCompuesta<String> getActividadesNombres() {
        return actividadesNombres;
    }

    public ListaCompuesta<Double> getPesos() {
        return pesos;
    }

    public void agregarActividad(String nombreActividad) {
        actividadesNombres.agregar(nombreActividad);
    }

    public void agregarActividad(String nombreActividad, double peso) {
        actividadesNombres.agregar(nombreActividad);
        pesos.agregar(peso);
    }
}
