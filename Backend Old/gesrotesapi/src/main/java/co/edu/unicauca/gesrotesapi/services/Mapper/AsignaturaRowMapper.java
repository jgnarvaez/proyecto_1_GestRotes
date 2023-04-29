package co.edu.unicauca.gesrotesapi.services.Mapper;
import org.springframework.jdbc.core.RowMapper;

import co.edu.unicauca.gesrotesapi.models.AsignaturaEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AsignaturaRowMapper implements RowMapper<AsignaturaEntity> {

    @Override
    public AsignaturaEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        AsignaturaEntity asignatura = new AsignaturaEntity();
        asignatura.setAsig_id(rs.getInt("asig_id"));
        asignatura.setAsig_nombre(rs.getString("asig_nombre"));

        return asignatura;
    }
}

