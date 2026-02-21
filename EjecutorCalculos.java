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
        
        // desapilar y calcular promedio en escala 0-10
        double sumaNotas = 0;
        int count = 0;
        while (!notas.estaVacia()) {
            Fraccion nota = notas.desapilar();
            if (nota != null) {
                // convertir cada nota a escala 0-10
                double notaSobre10 = nota.aDecimal() * 10;
                sumaNotas += notaSobre10;
                count++;
            }
        }
        
        if (count == 0) {
            return new Fraccion(0, 1);
        }
        
        double promedioSobre10 = sumaNotas / count;
        // Convertir a fracción sobre 10 (sin simplificar)
        int numerador = (int) Math.round(promedioSobre10 * 10);
        return new Fraccion(numerador, 10, false);
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
        
        // desapilar y sumar todas las notas normalizadas a escala 0-10
        double sumaNotas = 0;
        int count = 0;
        while (!notas.estaVacia()) {
            Fraccion nota = notas.desapilar();
            if (nota != null) {
                // convertir cada nota a escala 0-10
                double notaSobre10 = nota.aDecimal() * 10;
                sumaNotas += notaSobre10;
                count++;
            }
        }
        
        if (count == 0) {
            return new Fraccion(0, 1);
        }
        
        // Normalizar a escala 0-10 dividiendo entre el número de actividades
        double sumaSobre10 = sumaNotas / count;
        // Convertir a fracción sobre 10 (sin simplificar)
        int numerador = (int) Math.round(sumaSobre10 * 10);
        return new Fraccion(numerador, 10, false);
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
