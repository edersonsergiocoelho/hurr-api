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
 * Classe que representa uma marca de veículo.
 * <p>
 * Esta classe mapeia a tabela "vehicle_brand" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "vehicleBrandId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_brand")
public class VehicleBrand extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -2354532169797707959L;

    /**
     * Identificador único da marca do veículo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID vehicleBrandId;

    /**
     * Nome da marca do veículo.
     */
    @Column(name = "vehicle_brand_name", nullable = false, unique = true)
    private String vehicleBrandName;

    /**
     * Identificador do arquivo associado à marca de veículo.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

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