import java.util.Comparator;

public class ListaCompuesta<T> {
    private Nodo<T> cabeza;
    private int tamaño;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public ListaCompuesta() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            agregarRecursivo(cabeza, nuevoNodo);
        }
        tamaño++;
    }

    private void agregarRecursivo(Nodo<T> actual, Nodo<T> nuevoNodo) {
        if (actual.siguiente == null) {
            actual.siguiente = nuevoNodo;
        } else {
            agregarRecursivo(actual.siguiente, nuevoNodo);
        }
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño || cabeza == null) {
            return null;
        }
        return obtenerRecursivo(cabeza, indice);
    }

    private T obtenerRecursivo(Nodo<T> actual, int indice) {
        if (indice == 0) {
            return actual.dato;
        }
        if (actual.siguiente == null) {
            return null;
        }
        return obtenerRecursivo(actual.siguiente, indice - 1);
    }

    public T obtenerPrimero() {
        if (cabeza == null) {
            return null;
        }
        return cabeza.dato;
    }

    public T obtenerUltimo() {
        if (cabeza == null) {
            return null;
        }
        return obtenerUltimoRecursivo(cabeza);
    }

    private T obtenerUltimoRecursivo(Nodo<T> actual) {
        if (actual.siguiente == null) {
            return actual.dato;
        }
        return obtenerUltimoRecursivo(actual.siguiente);
    }

    public ListaCompuesta<T> filtrar(Comparator<T> comparador) {
        ListaCompuesta<T> nuevaLista = new ListaCompuesta<>();
        filtrarRecursivo(cabeza, comparador, nuevaLista);
        return nuevaLista;
    }

    private void filtrarRecursivo(Nodo<T> actual, Comparator<T> comparador, ListaCompuesta<T> nuevaLista) {
        if (actual == null) {
            return;
        }
        if (cumpleCriterio(actual.dato, comparador)) {
            nuevaLista.agregar(actual.dato);
        }
        filtrarRecursivo(actual.siguiente, comparador, nuevaLista);
    }

    private boolean cumpleCriterio(T elemento, Comparator<T> comparador) {
        return comparador.compare(elemento, elemento) == 0;
    }

    public void ordenar(Comparator<T> comparador) {
        if (tamaño <= 1 || cabeza == null) {
            return;
        }
        cabeza = ordenarRecursivo(cabeza, comparador);
    }

    private Nodo<T> ordenarRecursivo(Nodo<T> nodo, Comparator<T> comparador) {
        if (nodo == null || nodo.siguiente == null) {
            return nodo;
        }

        Nodo<T> mitad = obtenerMitad(nodo);
        Nodo<T> segunda = mitad.siguiente;
        mitad.siguiente = null;

        Nodo<T> izquierda = ordenarRecursivo(nodo, comparador);
        Nodo<T> derecha = ordenarRecursivo(segunda, comparador);

        return mezclar(izquierda, derecha, comparador);
    }

    private Nodo<T> obtenerMitad(Nodo<T> nodo) {
        Nodo<T> lento = nodo;
        Nodo<T> rapido = nodo.siguiente;

        while (rapido != null && rapido.siguiente != null) {
            lento = lento.siguiente;
            rapido = rapido.siguiente.siguiente;
        }
        return lento;
    }

    private Nodo<T> mezclar(Nodo<T> izq, Nodo<T> der, Comparator<T> comparador) {
        if (izq == null) {
            return der;
        }
        if (der == null) {
            return izq;
        }

        if (comparador.compare(izq.dato, der.dato) <= 0) {
            izq.siguiente = mezclar(izq.siguiente, der, comparador);
            return izq;
        } else {
            der.siguiente = mezclar(izq, der.siguiente, comparador);
            return der;
        }
    }

    public int tamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    // UNIÓN: Retorna una lista con todos los elementos de dos listas sin repetidos
    public ListaCompuesta<T> union(ListaCompuesta<T> otraLista) {
        ListaCompuesta<T> resultado = new ListaCompuesta<>();
        
        // Agregar todos los elementos de la lista actual
        agregarTodosRecursivo(this.cabeza, resultado);
        
        // Agregar elementos de la otra lista solo si no existen en la actual
        agregarSiNoExisteRecursivo(otraLista.cabeza, resultado);
        
        return resultado;
    }

    private void agregarTodosRecursivo(Nodo<T> actual, ListaCompuesta<T> resultado) {
        if (actual == null) {
            return;
        }
        resultado.agregar(actual.dato);
        agregarTodosRecursivo(actual.siguiente, resultado);
    }

    private void agregarSiNoExisteRecursivo(Nodo<T> actual, ListaCompuesta<T> resultado) {
        if (actual == null) {
            return;
        }
        if (!existeElemento(resultado.cabeza, actual.dato)) {
            resultado.agregar(actual.dato);
        }
        agregarSiNoExisteRecursivo(actual.siguiente, resultado);
    }

    private boolean existeElemento(Nodo<T> nodo, T elemento) {
        if (nodo == null) {
            return false;
        }
        if (nodo.dato.equals(elemento)) {
            return true;
        }
        return existeElemento(nodo.siguiente, elemento);
    }

    // INTERSECCIÓN: Retorna una lista con los elementos que están en ambas listas
    public ListaCompuesta<T> interseccion(ListaCompuesta<T> otraLista) {
        ListaCompuesta<T> resultado = new ListaCompuesta<>();
        
        // Agregar solo los elementos que existen en ambas listas
        agregarSoloComunesRecursivo(this.cabeza, otraLista, resultado);
        
        return resultado;
    }

    private void agregarSoloComunesRecursivo(Nodo<T> actual, ListaCompuesta<T> otraLista, ListaCompuesta<T> resultado) {
        if (actual == null) {
            return;
        }
        
        // Verificar si el elemento existe en la otra lista y no está ya en el resultado
        if (existeElemento(otraLista.cabeza, actual.dato) && 
            !existeElemento(resultado.cabeza, actual.dato)) {
            resultado.agregar(actual.dato);
        }
        
        agregarSoloComunesRecursivo(actual.siguiente, otraLista, resultado);
    }

    public String mostrar() {
        if (cabeza == null) {
            return "[]";
        }
        return "[" + mostrarRecursivo(cabeza) + "]";
    }

    private String mostrarRecursivo(Nodo<T> actual) {
        if (actual == null) {
            return "";
        }
        if (actual.siguiente == null) {
            return actual.dato.toString();
        }
        return actual.dato.toString() + ", " + mostrarRecursivo(actual.siguiente);
    }
}
