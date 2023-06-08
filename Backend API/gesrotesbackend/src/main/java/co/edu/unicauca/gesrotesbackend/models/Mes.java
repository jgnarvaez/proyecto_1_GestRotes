package co.edu.unicauca.gesrotesbackend.models;

public enum Mes {
    Enero(1), Febrero(2), Marzo(3), Abril(4), Mayo(5), Junio(6), Julio(7), Agosto(8), Septiembre(9), Octubre(10), Noviembre(11), Diciembre(12);

    private int numero;

    Mes(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public static Mes obtenerMesAnterior(Mes mes) {
        int ordinal = mes.ordinal();
        if (ordinal == 0) {
            // Si el mes es Enero, devolvemos Diciembre (el último mes)
            return Diciembre;
        } else {
            // Devolvemos el mes anterior al actual
            return values()[ordinal - 1];
        }
    }
}
