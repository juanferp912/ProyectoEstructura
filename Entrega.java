import java.util.Comparator;
public class Entrega{
    private String nombre;
    private String comentarios;
    private int nota;
    private String fechaEntrega;

    public Entrega(String nombre, String comentarios, int nota) {
        this.nombre = nombre;
        this.comentarios = comentarios;
        this.nota = nota;
    }

    public int getNota() { return nota;}
    public String getNombre() {return nombre;}
    public String getComentarios() { return comentarios;}
}