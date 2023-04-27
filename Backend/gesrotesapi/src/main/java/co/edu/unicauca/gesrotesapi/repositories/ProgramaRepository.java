package co.edu.unicauca.gesrotesapi.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.gesrotesapi.models.ProgramaEntity;
@Repository
public class ProgramaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ProgramaEntity> findAll()
    {
        List<ProgramaEntity> listadoProgramas = new ArrayList<>();
        String sql = "SELECT * FROM tbl_programa";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            ProgramaEntity programa = new ProgramaEntity();
            programa.setProg_id((Integer) row.get("prog_id"));
            programa.setProg_nombre((String) row.get("prog_nombre"));
            listadoProgramas.add(programa);
        }
        return listadoProgramas;
    }

    public ProgramaEntity findByID(int id) {
        String sql = "SELECT * FROM tbl_programa WHERE prog_id = ?";
        ProgramaEntity programa = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ProgramaEntity.class), id);
        return programa;
    }
}
