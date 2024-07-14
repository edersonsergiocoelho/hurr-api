package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
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
}