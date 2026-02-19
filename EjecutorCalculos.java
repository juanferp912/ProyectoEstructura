public class EjecutorCalculos {

    public static Fraccion ejecutarCalculo(Calculo calculo, Estudiante estudiante) {
        if (calculo.getTipo().equals("promedio")) {
            return calcularPromedio(calculo, estudiante);
        } else if (calculo.getTipo().equals("suma")) {
            return calcularSuma(calculo, estudiante);
        }
        return new Fraccion(0, 1);
    }

    private static Fraccion calcularPromedio(Calculo calculo, Estudiante estudiante) {
        Pila<Fraccion> notas = new Pila<>();
        
        // apilar todas las notas del estudiante en las actividades del cálculo
        for (int i = 0; i < calculo.getActividadesNombres().tamaño(); i++) {
            String nombreActividad = calculo.getActividadesNombres().obtener(i);
            if (nombreActividad != null) {
                Fraccion nota = obtenerNotaEstudianteEnActividad(estudiante, nombreActividad);
                if (nota != null) {
                    notas.apilar(nota);
                }
            }
        }
        
        // desapilar y sumar todas las notas
        int suma = 0;
        int count = 0;
        while (!notas.estaVacia()) {
            Fraccion nota = notas.desapilar();
            if (nota != null) {
                suma += nota.getNumerador();
                count++;
            }
        }
        
        if (count == 0) {
            return new Fraccion(0, 1);
        }
        
        return new Fraccion(suma / count, 1);
    }

    private static Fraccion calcularSuma(Calculo calculo, Estudiante estudiante) {
        Pila<Fraccion> notas = new Pila<>();
        
        // apilar todas las notas del estudiante en las actividades del cálculo
        for (int i = 0; i < calculo.getActividadesNombres().tamaño(); i++) {
            String nombreActividad = calculo.getActividadesNombres().obtener(i);
            if (nombreActividad != null) {
                Fraccion nota = obtenerNotaEstudianteEnActividad(estudiante, nombreActividad);
                if (nota != null) {
                    notas.apilar(nota);
                }
            }
        }
        
        // desapilar y sumar todas las notas
        int suma = 0;
        while (!notas.estaVacia()) {
            Fraccion nota = notas.desapilar();
            if (nota != null) {
                suma += nota.getNumerador();
            }
        }
        
        return new Fraccion(suma, 1);
    }

    private static Fraccion obtenerNotaEstudianteEnActividad(Estudiante estudiante, String nombreActividad) {
        for (int i = 0; i < estudiante.getEntregas().tamaño(); i++) {
            Entrega entrega = estudiante.getEntregas().obtener(i);
            if (entrega != null && entrega.getNombre().equals(nombreActividad)) {
                return entrega.getNota();
            }
        }
        return null;
    }
}
