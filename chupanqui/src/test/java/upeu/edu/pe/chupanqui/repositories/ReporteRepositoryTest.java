package upeu.edu.pe.chupanqui.repositories;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import upeu.edu.pe.chupanqui.models.Reporte;

@ExtendWith(MockitoExtension.class)
public class ReporteRepositoryTest {

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

        Optional<Reporte> result = reporteRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void testFindAll() {
        List<Reporte> reportes = List.of(reporte);

        given(reporteRepository.findAll()).willReturn(reportes);

        List<Reporte> result = reporteRepository.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
