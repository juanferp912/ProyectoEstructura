import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CargadorCalculos {

    public static ListaCompuesta<Calculo> cargarCalculos(String ruta) {
        ListaCompuesta<Calculo> calculos = new ListaCompuesta<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea = br.readLine();
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    String nombre = datos[0].trim();
                    String tipo = datos[1].trim();
                    String actividadesStr = datos[2].trim();
                    
                    Calculo calculo = new Calculo(nombre, tipo);
                    
                    // Parsear actividades con pesos
                    String[] actividadesConPeso = actividadesStr.split("\\|");
                    for (String item : actividadesConPeso) {
                        item = item.trim();
                        if (item.contains(":")) {
                            // Tiene peso
                            String[] partes = item.split(":");
                            String actividad = partes[0].trim();
                            double peso = Double.parseDouble(partes[1].trim());
                            calculo.agregarActividad(actividad, peso);
                        } else {
                            // Sin peso (promedio simple)
                            calculo.agregarActividad(item);
                        }
                    }
                    
                    calculos.agregar(calculo);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al cargar cálculos: " + e.getMessage());
        }
        
        return calculos;
    }
}
