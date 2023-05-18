package co.edu.unicauca.gesrotesbackend.services.Utilities;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class Horario implements Comparable<Horario> {
    public Time horaInicio;
    public Time horaFin;

    public Horario(String horaInicio, String horaFin) {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        try {
            this.horaInicio = new Time(formato.parse(horaInicio).getTime());
            this.horaFin = new Time(formato.parse(horaFin).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Horario otroHorario) {
        return this.horaInicio.compareTo(otroHorario.horaInicio);
    }

    public boolean estaEnRango(LocalTime inicio, LocalTime fin) {
        LocalTime horaInicioLocal = this.horaInicio.toLocalTime();
        LocalTime horaFinLocal = this.horaFin.toLocalTime();

        return (horaInicioLocal.compareTo(inicio) >= 0 && horaInicioLocal.compareTo(fin) < 0) ||
                (horaFinLocal.compareTo(inicio) > 0 && horaFinLocal.compareTo(fin) <= 0) ||
                (horaInicioLocal.compareTo(inicio) < 0 && horaFinLocal.compareTo(fin) > 0);
    }
}
