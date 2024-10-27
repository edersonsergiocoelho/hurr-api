package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * Classe que representa a transmissão de um veículo.
 * <p>
 * Esta classe mapeia a tabela "vehicle_transmission" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "vehicleTransmissionId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_transmission")
public class VehicleTransmission extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -8098568882258805043L;

    /**
     * Identificador único da transmissão do veículo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_transmission_id")
    private UUID vehicleTransmissionId;

    /**
     * Nome da transmissão do veículo.
     */
    @Column(name = "vehicle_transmission_name", length = 100, nullable = false, unique = true)
    private String vehicleTransmissionName;

    /**
     * Identificador do arquivo associado à transmissão do veículo.
     */
    @Column(name = "file_id")
    private UUID fileId;

    /**
     * Método de callback executado antes da persistência da entidade.
     */
    @PrePersist
    protected void prePersist() {
        if (this.getCreatedDate() == null) {
            this.setCreatedDate(Instant.now());
        }
        if (this.getEnabled() == null) {
            this.setEnabled(Boolean.TRUE);
        }
    }
}