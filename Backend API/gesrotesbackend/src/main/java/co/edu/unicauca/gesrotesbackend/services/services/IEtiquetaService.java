package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.services.DTO.EscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevaEtiquetaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ServicioDTO;

import java.util.List;

public interface IEtiquetaService {
    // Registrar una etiqueta
    NuevaEtiquetaDTO crearEtiqueta(NuevaEtiquetaDTO nuevaEtiqueta);
    // Obtener lista de etiquetas creadas(solo tienen nombre y escenario)
    List<EtiquetaCreadaDTO> obtenerEtiquetasCreadas();
    // Obtener lista de etiquetas asociadas(con servicio)
    List<EtiquetaConServicioDTO> obtenerEtiquetasAsociadas();
    // Obtener lista de etiquetas filtradas por un escenario
    List<EtiquetaPorEscenarioDTO> obtenerEtiquetasPorEscenario(int idEscenario);
    // Obtener lista de los escenarios
    List<EscenarioDTO> obetenerEscenarios();
    // Obtener lista de los servicios
    List<ServicioDTO> obtenerServicios();
    // Asociar un servicio a una etiqueta
    AsociacionEtiquetaServicioDTO asociarEtiqueta(int idEtiqueta, int idServicio);
    // Eliminar una etiqueta
    void eliminarEtiqueta(int idEtiqueta);
    // Eliminar servicio asociado a una etiqueta
    void eliminarAsociacion(int idEtiqueta);
}
