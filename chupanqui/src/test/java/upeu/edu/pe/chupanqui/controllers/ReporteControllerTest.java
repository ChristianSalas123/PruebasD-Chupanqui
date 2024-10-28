package upeu.edu.pe.chupanqui.controllers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import upeu.edu.pe.chupanqui.models.Reporte;
import upeu.edu.pe.chupanqui.services.ReporteService;

@ExtendWith(MockitoExtension.class)
public class ReporteControllerTest {

    @InjectMocks
    private ReporteController reporteController;

    @Mock
    private ReporteService reporteService;

    private Reporte reporte;

    @BeforeEach
    public void setUp() {
        reporte = new Reporte();
        reporte.setId(1L);
        reporte.setTitulo("Desarrollador python");
        reporte.setDescripcion("Con 3 años de experiencia.");
        reporte.setEmpresa("Empresa XYZ");
        reporte.setSalario(5000.0);
    }

    @Test
    public void testGetAllReportes() {
        List<Reporte> listaReportes = Arrays.asList(reporte);

        // Simular el comportamiento del servicio
        given(reporteService.findAll()).willReturn(listaReportes);

        // Llamar al controlador y verificar la respuesta
        ResponseEntity<List<Reporte>> response = reporteController.getAllReportes();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Desarrollador python", response.getBody().get(0).getTitulo());
    }

    @Test
    public void testCreateReporte() {
        given(reporteService.save(Mockito.any(Reporte.class))).willReturn(reporte);

        ResponseEntity<Reporte> response = reporteController.createReporte(reporte);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Desarrollador python", response.getBody().getTitulo());
    }

    @Test
    public void testGetReporteByIdNotFound() {
        // Simular que el servicio lanza una excepción cuando el reporte no se encuentra
        given(reporteService.findById(1L)).willThrow(new RuntimeException("Reporte no encontrado"));

        // Llamar al controlador y verificar que responde con un error 404
        Exception exception = assertThrows(RuntimeException.class, () -> {
            reporteController.getReporteById(1L);
        });

        assertEquals("Reporte no encontrado", exception.getMessage());
    }
}
