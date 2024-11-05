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
 * Classe que representa o arquivo de seguro do veículo do cliente.
 * <p>
 * Esta classe mapeia a tabela "customer_vehicle_file_insurance" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "customerVehicleFileInsuranceId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_vehicle_file_insurance")
public class CustomerVehicleFileInsurance extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 1361567295578821635L;

    /**
     * Identificador único do arquivo de seguro do veículo do cliente.
     */
    @Id
    @GeneratedValue
    @Column(name = "customer_vehicle_file_insurance_id", nullable = false, unique = true)
    private UUID customerVehicleFileInsuranceId;

    /**
     * Identificador único do veículo do cliente associado.
     */
    @ManyToOne
    @JoinColumn(name = "customer_vehicle_id", nullable = false)
    private CustomerVehicle customerVehicle;

    /**
     * Tipo de conteúdo do arquivo (MIME type).
     */
    @Column(name = "content_type", nullable = false, length = 50)
    private String contentType;

    /**
     * Nome original do arquivo.
     */
    @Column(name = "original_file_name", nullable = false, length = 1000)
    private String originalFileName;

    /**
     * Dados do arquivo em formato de array de bytes.
     */
    @Column(name = "data_as_byte_array", nullable = false)
    private byte[] dataAsByteArray;

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