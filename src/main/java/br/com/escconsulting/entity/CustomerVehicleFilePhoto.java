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
 * Classe que representa a foto do veículo do cliente.
 * <p>
 * Esta classe mapeia a tabela "customer_vehicle_file_photo" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "customerVehicleFilePhotoId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_vehicle_file_photo")
public class CustomerVehicleFilePhoto extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -3650281586721901291L;

    /**
     * Identificador único da foto do veículo do cliente.
     */
    @Id
    @GeneratedValue
    @Column(name = "customer_vehicle_file_photo_id", nullable = false, unique = true)
    private UUID customerVehicleFilePhotoId;

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
     * Indica se a foto é a foto de capa do veículo do cliente.
     */
    @Column(name = "cover_photo", nullable = false)
    private Boolean coverPhoto;

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