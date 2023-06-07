package co.edu.unicauca.gesrotesbackend.models;

public enum Mes {
    Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, Diciembre;

    public static String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
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
