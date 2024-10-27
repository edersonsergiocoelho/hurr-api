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
 * Classe que representa um tipo de combustível de veículo.
 * <p>
 * Esta classe mapeia a tabela "vehicle_fuel_type" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "vehicleFuelTypeId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_fuel_type")
public class VehicleFuelType extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 1870227821843440688L;

    /**
     * Identificador único do tipo de combustível do veículo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_fuel_type_id")
    private UUID vehicleFuelTypeId;

    /**
     * Nome do tipo de combustível do veículo.
     */
    @Column(name = "vehicle_fuel_type_name", nullable = false, unique = true)
    private String vehicleFuelTypeName;

    /**
     * Identificador do arquivo associado ao tipo de combustível do veículo.
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