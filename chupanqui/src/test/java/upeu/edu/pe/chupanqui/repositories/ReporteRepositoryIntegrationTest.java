package upeu.edu.pe.chupanqui.repositories;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import upeu.edu.pe.chupanqui.models.Reporte;

@SpringBootTest
public class ReporteRepositoryIntegrationTest {

    @Autowired
    private ReporteRepository reporteRepository;

    private Reporte reporte;

    @BeforeEach
    public void setUp() {
        // Limpiar la base de datos antes de cada prueba
        reporteRepository.deleteAll();
        
        // Crear un reporte de ejemplo
        reporte = new Reporte();
        reporte.setTitulo("Desarrollador Java");
        reporte.setDescripcion("Desarrollador Java con experiencia.");
        reporte.setEmpresa("Empresa XYZ");
        reporte.setFechaPublicacion(new Date());
        reporte.setFechaCierre(new Date());
        reporte.setSalario(5000.0);
        
        reporteRepository.save(reporte);
    }

    @Test
    public void testFindAll() {
        List<Reporte> reportes = reporteRepository.findAll();
        assertEquals(1, reportes.size());
        assertEquals("Desarrollador Java", reportes.get(0).getTitulo());
    }

    @Test
    public void testFindById() {
        Reporte reporte = reporteRepository.findById(this.reporte.getId()).orElse(null);
        assertNotNull(reporte);
        assertEquals("Desarrollador Java", reporte.getTitulo());
    }

    @Test
    public void testCreateReporte() {
        Reporte nuevoReporte = new Reporte();
        nuevoReporte.setTitulo("Analista de Datos");
        nuevoReporte.setDescripcion("Experiencia en SQL");
        nuevoReporte.setEmpresa("Empresa ABC");
        nuevoReporte.setFechaPublicacion(new Date());
        nuevoReporte.setFechaCierre(new Date());
        nuevoReporte.setSalario(4500.0);

        Reporte createdReporte = reporteRepository.save(nuevoReporte);
        assertNotNull(createdReporte);
        assertEquals("Analista de Datos", createdReporte.getTitulo());
    }

    @Test
    public void testDeleteReporte() {
        reporteRepository.delete(reporte);
        List<Reporte> reportes = reporteRepository.findAll();
        assertEquals(0, reportes.size());
    }
}
