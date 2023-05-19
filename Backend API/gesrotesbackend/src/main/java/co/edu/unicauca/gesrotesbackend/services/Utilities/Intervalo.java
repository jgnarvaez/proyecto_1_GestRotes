package co.edu.unicauca.gesrotesbackend.services.Utilities;

import java.sql.Time;

public class Intervalo {
    public Time inicio;
    public Time fin;

    public Intervalo(HorarioJornada horario) {
        this.inicio = horario.horaInicio;
        this.fin = horario.horaFin;
    }
}
