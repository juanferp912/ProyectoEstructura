public class Calculo {
    private String nombre;
    private String tipo;
    private ListaCompuesta<String> actividadesNombres;

    public Calculo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.actividadesNombres = new ListaCompuesta<>();
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

    public void agregarActividad(String nombreActividad) {
        actividadesNombres.agregar(nombreActividad);
    }
}
