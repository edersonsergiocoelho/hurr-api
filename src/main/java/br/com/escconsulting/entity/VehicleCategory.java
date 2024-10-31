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
 * Classe que representa uma categoria de veículo.
 * <p>
 * Esta classe mapeia a tabela "vehicleCategory" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "vehicleCategoryId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicleCategory")
public class VehicleCategory extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 8432591261011933818L;

    /**
     * Identificador único da categoria do veículo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicleCategory_id", nullable = false)
    private UUID vehicleCategoryId;

    /**
     * Nome da categoria do veículo.
     */
    @Column(name = "vehicleCategory_name", nullable = false, length = 100, unique = true)
    private String vehicleCategoryName;

    /**
     * Identificador do arquivo associado à categoria de veículo.
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