public class Pila<T> {
    private Nodo<T> cima;
    private int tamaño;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public Pila() {
        this.cima = null;
        this.tamaño = 0;
    }

    public void apilar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
        tamaño++;
    }

    public T desapilar() {
        if (cima == null) {
            return null;
        }
        
        T dato = cima.dato;
        cima = cima.siguiente;
        tamaño--;
        return dato;
    }

    public T obtenerCima() {
        if (cima == null) {
            return null;
        }
        return cima.dato;
    }

    public int tamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }
}
