package upeu.edu.pe.chupanqui.services;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import upeu.edu.pe.chupanqui.models.Reporte;
import upeu.edu.pe.chupanqui.repositories.ReporteRepository;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {

    @InjectMocks
    private ReporteServiceImpl reporteService;

    @Mock
    private ReporteRepository reporteRepository;

    private Reporte reporte;

    @BeforeEach
    public void setUp() {
        reporte = new Reporte();
        reporte.setId(1L);
        reporte.setTitulo("Desarrollador Java");
        reporte.setDescripcion("Con 3 años de experiencia.");
        reporte.setEmpresa("Empresa XYZ");
        reporte.setSalario(5000.0);
    }

    @Test
    public void testFindById() {
        given(reporteRepository.findById(1L)).willReturn(Optional.of(reporte));

        Reporte result = reporteService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Desarrollador Java", result.getTitulo());
    }

    @Test
    public void testFindAll() {
        List<Reporte> reportes = List.of(reporte);

        given(reporteRepository.findAll()).willReturn(reportes);

        List<Reporte> result = reporteService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Desarrollador Java", result.get(0).getTitulo());
    }

    @Test
    public void testSaveReporte() {
        given(reporteRepository.save(Mockito.any(Reporte.class))).willReturn(reporte);

        Reporte result = reporteService.save(reporte);

        assertNotNull(result);
        assertEquals("Desarrollador Java", result.getTitulo());
    }

    @Test
    public void testUpdateReporte() {
        Reporte actualizada = new Reporte();
        actualizada.setId(1L);  // Asegúrate de establecer el ID para evitar el PotentialStubbingProblem
        actualizada.setTitulo("Desarrollador Full Stack");
        actualizada.setDescripcion("Con experiencia en JavaScript");
        actualizada.setEmpresa("Empresa XYZ");
        actualizada.setSalario(6000.0);

        given(reporteRepository.findById(1L)).willReturn(Optional.of(reporte));
        given(reporteRepository.save(actualizada)).willReturn(actualizada);

        Reporte result = reporteService.update(actualizada, 1L);

        assertNotNull(result);
        assertEquals("Desarrollador Full Stack", result.getTitulo());
        assertEquals(6000.0, result.getSalario());
    }

    @Test
public void testDeleteReporte() {
    // Configura el reporte existente en el repositorio simulado
    given(reporteRepository.findById(1L)).willReturn(Optional.of(reporte));
    willDoNothing().given(reporteRepository).delete(reporte);

    // Llama al método de eliminación
    reporteService.delete(1L);

    // Verifica que el método delete fue llamado una vez con el objeto reporte
    verify(reporteRepository, times(1)).delete(reporte);
}
}
