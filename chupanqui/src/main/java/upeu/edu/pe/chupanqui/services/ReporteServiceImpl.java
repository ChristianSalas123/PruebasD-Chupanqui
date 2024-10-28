package upeu.edu.pe.chupanqui.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upeu.edu.pe.chupanqui.exceptions.ResourceNotFoundException;
import upeu.edu.pe.chupanqui.models.Reporte;
import upeu.edu.pe.chupanqui.repositories.ReporteRepository;

@Service
@Transactional
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    @Override
    public List<Reporte> findAll() {
        return reporteRepository.findAll();
    }

    @Override
    public Reporte findById(Long id) {
        return reporteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado con el id: " + id));
    }

    @Override
    public Reporte update(Reporte reporte, Long id) {
        Reporte existingReporte = reporteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado con el id: " + id));
        
        existingReporte.setTitulo(reporte.getTitulo());
        existingReporte.setDescripcion(reporte.getDescripcion());
        existingReporte.setEmpresa(reporte.getEmpresa());
        existingReporte.setFechaPublicacion(reporte.getFechaPublicacion());
        existingReporte.setFechaCierre(reporte.getFechaCierre());
        existingReporte.setSalario(reporte.getSalario());

        return reporteRepository.save(existingReporte);
    }

    @Override
    public void delete(Long id) {
        Reporte reporte = reporteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado con el id: " + id));
        
        reporteRepository.delete(reporte);
    }
}
