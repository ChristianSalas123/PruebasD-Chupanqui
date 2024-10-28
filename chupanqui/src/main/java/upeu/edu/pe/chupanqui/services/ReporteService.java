package upeu.edu.pe.chupanqui.services;

import java.util.List;

import upeu.edu.pe.chupanqui.models.Reporte;

public interface ReporteService {
    Reporte save(Reporte reporte);
    List<Reporte> findAll();
    Reporte findById(Long id);
    Reporte update(Reporte reporte, Long id);
    void delete(Long id);
}
