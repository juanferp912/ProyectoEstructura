import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CargadorDatos {

    public static Curso cargarCurso(String nombreCurso, String codigoCurso, double notaMinima, 
                                     String profesor, int creditos, String rutaEstudiantes, 
                                     String rutaActividades, String rutaEntregas) {
        Curso curso = new Curso(nombreCurso, codigoCurso, notaMinima, profesor, creditos);
        
        cargarEstudiantes(curso, rutaEstudiantes);
        cargarActividades(curso, rutaActividades);
        cargarEntregas(curso, rutaEntregas);
        
        return curso;
    }

    private static void cargarEstudiantes(Curso curso, String ruta) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea = br.readLine();
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    String codigo = datos[0].trim();
                    String nombre = datos[1].trim();
                    String correo = datos[2].trim();
                    
                    Estudiante estudiante = new Estudiante(nombre, codigo, correo);
                    curso.getEstudiantes().agregar(estudiante);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al cargar estudiantes: " + e.getMessage());
        }
    }

    private static void cargarActividades(Curso curso, String ruta) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea = br.readLine();
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    String nombre = datos[0].trim();
                    String descripcion = datos[1].trim();
                    String fechaLimite = datos[2].trim();
                    double puntajeMaximo = Double.parseDouble(datos[3].trim());
                    
                    Actividad actividad = new Actividad(nombre, descripcion, fechaLimite, puntajeMaximo);
                    curso.getActividades().agregar(actividad);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al cargar actividades: " + e.getMessage());
        }
    }

    private static void cargarEntregas(Curso curso, String ruta) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea = br.readLine();
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 5) {
                    String codigoEstudiante = datos[0].trim();
                    String nombreActividad = datos[1].trim();
                    int numerador = Integer.parseInt(datos[2].trim());
                    int denominador = Integer.parseInt(datos[3].trim());
                    String fechaEntrega = datos[4].trim();
                    
                    Fraccion nota = new Fraccion(numerador, denominador);
                    Entrega entrega = new Entrega(nombreActividad, "", nota, fechaEntrega);
                    
                    asignarEntregaAEstudiante(curso, codigoEstudiante, entrega);
                    asignarEntregaAActividad(curso, nombreActividad, entrega);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al cargar entregas: " + e.getMessage());
        }
    }

    private static void asignarEntregaAEstudiante(Curso curso, String codigoEstudiante, Entrega entrega) {
        for (int i = 0; i < curso.getEstudiantes().tamaño(); i++) {
            Estudiante est = curso.getEstudiantes().obtener(i);
            if (est != null && est.getCodigo().equals(codigoEstudiante)) {
                est.getEntregas().agregar(entrega);
                return;
            }
        }
    }

    private static void asignarEntregaAActividad(Curso curso, String nombreActividad, Entrega entrega) {
        for (int i = 0; i < curso.getActividades().tamaño(); i++) {
            Actividad act = curso.getActividades().obtener(i);
            if (act != null && act.getNombre().equals(nombreActividad)) {
                act.getEntregas().agregar(entrega);
                return;
            }
        }
    }
}
