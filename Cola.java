public class Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public Cola() {
        this.inicio = null;
        this.fin = null;
        this.tamaño = 0;
    }

    public void encolar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        
        if (fin == null) {
            inicio = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
        tamaño++;
    }

    public T desencolar() {
        if (inicio == null) {
            return null;
        }
        
        T dato = inicio.dato;
        inicio = inicio.siguiente;
        
        if (inicio == null) {
            fin = null;
        }
        
        tamaño--;
        return dato;
    }

    public T obtenerPrimero() {
        if (inicio == null) {
            return null;
        }
        return inicio.dato;
    }

    public int tamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }
}
