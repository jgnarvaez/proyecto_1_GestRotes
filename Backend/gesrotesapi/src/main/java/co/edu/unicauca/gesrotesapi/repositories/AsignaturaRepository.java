package co.edu.unicauca.gesrotesapi.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.gesrotesapi.models.AsignaturaEntity;

@Repository
public class AsignaturaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AsignaturaEntity> findAll()
    {
        List<AsignaturaEntity> listadoAsignaturas = new ArrayList<>();
        String sql = "SELECT * FROM tbl_asignatura";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            AsignaturaEntity asignatura = new AsignaturaEntity();
            asignatura.setAsig_id((Integer) row.get("asig_id"));
            asignatura.setAsig_nombre((String) row.get("asig_nombre"));
            asignatura.setProg_id((Integer) row.get("prog_id"));
            asignatura.setCoo_id((Integer) row.get("coo_id"));
            listadoAsignaturas.add(asignatura);
        }
        return listadoAsignaturas;
    }

    public AsignaturaEntity findByID(int id) {
        String sql = "SELECT * FROM tbl_asignatura WHERE asig_id = ?";
        AsignaturaEntity asignatura = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(AsignaturaEntity.class), id);
        return asignatura;
    }        

}
