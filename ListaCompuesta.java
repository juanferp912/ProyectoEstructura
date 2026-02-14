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

    public int tamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }
}