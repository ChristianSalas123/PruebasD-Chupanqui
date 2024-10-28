package upeu.edu.pe.chupanqui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upeu.edu.pe.chupanqui.models.Reporte;
import upeu.edu.pe.chupanqui.services.ReporteService;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public ResponseEntity<Reporte> createReporte(@RequestBody Reporte reporte) {
        Reporte createdReporte = reporteService.save(reporte);
        return ResponseEntity.ok(createdReporte);
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> getAllReportes() {
        List<Reporte> reportes = reporteService.findAll();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getReporteById(@PathVariable Long id) {
        Reporte reporte = reporteService.findById(id);
        return ResponseEntity.ok(reporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> updateReporte(@RequestBody Reporte reporte, @PathVariable Long id) {
        Reporte updatedReporte = reporteService.update(reporte, id);
        return ResponseEntity.ok(updatedReporte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable Long id) {
        reporteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
