package upeu.edu.pe.chupanqui.controllers;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import upeu.edu.pe.chupanqui.models.Reporte;
import upeu.edu.pe.chupanqui.repositories.ReporteRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReporteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ReporteRepository reporteRepository;

    private Reporte reporte;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        
        // Limpiar la base de datos antes de cada prueba
        reporteRepository.deleteAll();
        
        // Crear un reporte para usar en las pruebas
        reporte = new Reporte();
        reporte.setTitulo("Desarrollador Java");
        reporte.setDescripcion("Desarrollador Java con 3 años de experiencia.");
        reporte.setEmpresa("Empresa XYZ");
        reporte.setFechaPublicacion(new Date());
        reporte.setFechaCierre(new Date());
        reporte.setSalario(5000.0);
        reporteRepository.save(reporte);
    }

    @Test
    public void testGetAllReportes() {
        given()
            .when().get("/api/reportes")
            .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].titulo", equalTo("Desarrollador Java"));
    }

    @Test
    public void testGetReporteById() {
        Long id = reporte.getId();
        given()
            .when().get("/api/reportes/{id}", id)
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Desarrollador Java"))
            .body("empresa", equalTo("Empresa XYZ"));
    }

    @Test
    public void testCreateReporte() {
        Reporte newReporte = new Reporte();
        newReporte.setTitulo("Analista de Datos");
        newReporte.setDescripcion("Analista con experiencia en Python y SQL.");
        newReporte.setEmpresa("Empresa ABC");
        newReporte.setFechaPublicacion(new Date());
        newReporte.setFechaCierre(new Date());
        newReporte.setSalario(4500.0);

        given()
            .contentType(ContentType.JSON)
            .body(newReporte)
            .when().post("/api/reportes")
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Analista de Datos"))
            .body("empresa", equalTo("Empresa ABC"));
    }

    @Test
    public void testUpdateReporte() {
        Long id = reporte.getId();
        reporte.setTitulo("Desarrollador Full Stack");

        given()
            .contentType(ContentType.JSON)
            .body(reporte)
            .when().put("/api/reportes/{id}", id)
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Desarrollador Full Stack"));
    }

    @Test
    public void testDeleteReporte() {
        Long id = reporte.getId();

        given()
            .when().delete("/api/reportes/{id}", id)
            .then()
            .statusCode(204);

        List<Reporte> allReportes = reporteRepository.findAll();
        assertEquals(0, allReportes.size());
    }
}
