package upeu.edu.pe.chupanqui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upeu.edu.pe.chupanqui.models.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
}
