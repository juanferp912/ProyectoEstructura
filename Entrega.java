public class Entrega {
    private String nombre;
    private String comentarios;
    private Fraccion nota;
    private String fechaEntrega;
    private String estado;

    public Entrega(String nombre, String comentarios, Fraccion nota, String fechaEntrega) {
        this.nombre = nombre;
        this.comentarios = comentarios;
        this.nota = nota;
        this.fechaEntrega = fechaEntrega;
        this.estado = "pendiente";
    }

    public String getNombre() {
        return nombre;
    }

    public String getComentarios() {
        return comentarios;
    }

    public Fraccion getNota() {
        return nota;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setNota(Fraccion nota) {
        this.nota = nota;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}