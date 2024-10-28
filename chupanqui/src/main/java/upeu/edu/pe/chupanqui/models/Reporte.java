package upeu.edu.pe.chupanqui.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reporte")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Size(max = 100)
    @Column(name = "empresa", nullable = false, length = 100)
    private String empresa;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_publicacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_cierre", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;

    @NotNull
    @Column(name = "salario", nullable = false)
    private Double salario;
}
