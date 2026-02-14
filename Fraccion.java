public class Fraccion implements Comparable<Fraccion> {
    private int numerador;
    private int denominador;

    public Fraccion(int numerador, int denominador) {
        if (denominador == 0) {
            denominador = 1;
        }
        this.numerador = numerador;
        this.denominador = denominador;
        simplificar();
    }

    private void simplificar() {
        int mcd = calcularMCD(Math.abs(numerador), Math.abs(denominador));
        numerador = numerador / mcd;
        denominador = denominador / mcd;
        if (denominador < 0) {
            numerador = -numerador;
            denominador = -denominador;
        }
    }

    private int calcularMCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return calcularMCD(b, a % b);
    }

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public double aDecimal() {
        return (double) numerador / denominador;
    }

    @Override
    public int compareTo(Fraccion otra) {
        int comparacion = numerador * otra.denominador - otra.numerador * denominador;
        if (comparacion > 0) {
            return 1;
        } else if (comparacion < 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Fraccion)) {
            return false;
        }
        Fraccion otra = (Fraccion) obj;
        return numerador == otra.numerador && denominador == otra.denominador;
    }

    @Override
    public String toString() {
        return numerador + "/" + denominador;
    }
}
