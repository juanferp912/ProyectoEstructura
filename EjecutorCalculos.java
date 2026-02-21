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
        
        //  suma de notas / cantidad de actividades
        double sumaNotas = 0;
        int count = 0;
        while (!notas.estaVacia()) {
            Fraccion nota = notas.desapilar();
            if (nota != null) {
                sumaNotas += nota.aDecimal();  
                count++;
            }
        }
        
        if (count == 0) {
            return new Fraccion(0, 1);
        }
        
        // Promedio: suma / cantidad, convertido a escala 0-10 y retornado como fracción
        double promedioDp = sumaNotas / count;
        int numerador = (int) Math.round(promedioDp * 10);
        return new Fraccion(numerador, 10, false);
    }

    private static Fraccion calcularSuma(Calculo calculo, Estudiante estudiante) {
        // Si tiene peso es suma ponderada
        if (calculo.getPesos().tamaño() > 0) {
            return calcularSumaPonderada(calculo, estudiante);
        }
        
        // Si no es promedio simple
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
        
        // Calcular suma promediada como en un boletín real: suma de notas / cantidad de actividades
        double sumaNotas = 0;
        int count = 0;
        while (!notas.estaVacia()) {
            Fraccion nota = notas.desapilar();
            if (nota != null) {
                sumaNotas += nota.aDecimal();  // Convertir a valor decimal
                count++;
            }
        }
        
        if (count == 0) {
            return new Fraccion(0, 1);
        }
        
        // Suma: suma / cantidad, convertido a escala 0-10 y retornado como fracción
        double sumaDp = sumaNotas / count;
        int numerador = (int) Math.round(sumaDp * 10);
        return new Fraccion(numerador, 10, false);
    }

    private static Fraccion calcularSumaPonderada(Calculo calculo, Estudiante estudiante) {
        double sumaPonderada = 0;
        
        // Iterar sobre actividades y pesos
        for (int i = 0; i < calculo.getActividadesNombres().tamaño(); i++) {
            String nombreActividad = calculo.getActividadesNombres().obtener(i);
            Double peso = calculo.getPesos().obtener(i);
            
            if (nombreActividad != null && peso != null) {
                Fraccion nota = obtenerNotaEstudianteEnActividad(estudiante, nombreActividad);
                if (nota != null) {
                    sumaPonderada += nota.aDecimal() * peso;
                }
            }
        }
        
        // Convertir a escala 0-10
        int numerador = (int) Math.round(sumaPonderada * 10);
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
