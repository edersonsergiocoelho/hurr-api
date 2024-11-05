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
 * Classe que representa a aprovação de um veículo de cliente.
 * <p>
 * Esta classe mapeia a tabela "customer_vehicle_approved" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "customerVehicleApprovedId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_vehicle_approved")
public class CustomerVehicleApproved extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 7929375791833446602L;

    /**
     * Identificador único da aprovação do veículo do cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_vehicle_approved_id")
    private UUID customerVehicleApprovedId;

    /**
     * Veículo do cliente associado.
     */
    @ManyToOne
    @JoinColumn(name = "customer_vehicle_id", nullable = false)
    private CustomerVehicle customerVehicle;

    /**
     * Identificador do usuário que aprovou o veículo.
     */
    @Column(name = "approved_by")
    private UUID approvedBy;

    /**
     * Identificador do usuário que reprovou o veículo.
     */
    @Column(name = "reproved_by")
    private UUID reprovedBy;

    /**
     * Mensagem de aprovação ou reprovação.
     */
    @Column(name = "message")
    private String message;

    /**
     * Identificador do usuário que criou o registro.
     */
    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    /**
     * Identificador do usuário que modificou o registro.
     */
    @Column(name = "modified_by")
    private UUID modifiedBy;

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