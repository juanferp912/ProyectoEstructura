import java.util.Comparator;
public class Entrega{
    private String nombre;
    private String comentarios;
    private double nota;
    private String fechaEntrega;

    public Entrega(String nombre, String comentarios, double nota) {
        this.nombre = nombre;
        this.comentarios = comentarios;
        this.nota = nota;
    }

    public double getNota() { return nota;}
    public String getNombre() {return nombre;}
    public String getComentarios() { return comentarios;}
}