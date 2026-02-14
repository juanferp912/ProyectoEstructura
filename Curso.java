public class Curso {
    private String nombre;
    private String codigo;
    private double notaMinima;
    private String profesor;
    private int creditos;
    private ListaCompuesta<Estudiante> estudiantes;
    private ListaCompuesta<Actividad> actividades;

    public Curso(String nombre, String codigo, double notaMinima, String profesor, int creditos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.notaMinima = notaMinima;
        this.profesor = profesor;
        this.creditos = creditos;
        this.estudiantes = new ListaCompuesta<>();
        this.actividades = new ListaCompuesta<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getNotaMinima() {
        return notaMinima;
    }

    public String getProfesor() {
        return profesor;
    }

    public int getCreditos() {
        return creditos;
    }

    public ListaCompuesta<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public ListaCompuesta<Actividad> getActividades() {
        return actividades;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNotaMinima(double notaMinima) {
        this.notaMinima = notaMinima;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    // Operaciones TDA
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.agregar(estudiante);
    }

    public void agregarActividad(Actividad actividad) {
        actividades.agregar(actividad);
    }

    public int contarEstudiantes() {
        return estudiantes.tamaño();
    }

    public int contarActividades() {
        return actividades.tamaño();
    }

    public double obtenerPromedioEstudiantes() {
        if (estudiantes.estaVacia()) {
            return 0;
        }
        double sumaPromedios = 0;
        for (int i = 0; i < estudiantes.tamaño(); i++) {
            sumaPromedios += estudiantes.obtener(i).getPromedio();
        }
        return sumaPromedios / estudiantes.tamaño();
    }

    public int contarEstudiantesAprobados() {
        int aprobados = 0;
        for (int i = 0; i < estudiantes.tamaño(); i++) {
            if (estudiantes.obtener(i).getPromedio() >= notaMinima) {
                aprobados++;
            }
        }
        return aprobados;
    }

    public int contarEstudiantesReprobados() {
        return estudiantes.tamaño() - contarEstudiantesAprobados();
    }
}
