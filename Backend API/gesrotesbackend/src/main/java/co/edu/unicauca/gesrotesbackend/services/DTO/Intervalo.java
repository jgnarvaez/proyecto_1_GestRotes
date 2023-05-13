package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Time;

public class Intervalo {
    public Time inicio;
    public Time fin;

    public Intervalo(Horario horario) {
        this.inicio = horario.horaInicio;
        this.fin = horario.horaFin;
    }
}
