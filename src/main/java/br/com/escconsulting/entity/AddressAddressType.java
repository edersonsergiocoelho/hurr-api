package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * Classe que representa a associação entre endereço e tipo de endereço.
 * <p>
 * Esta classe mapeia a tabela "address_address_type" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address_address_type")
public class AddressAddressType extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -1394465772037897566L;

    /**
     * ID composto da associação entre endereço e tipo de endereço.
     */
    @EmbeddedId
    private AddressAddressTypeId id;

    /**
     * Endereço associado.
     */
    @JsonIgnore
    @ManyToOne
    @MapsId("addressId")
    @JoinColumn(name = "address_id")
    private Address address;

    /**
     * Tipo de endereço associado.
     */
    @ManyToOne
    @MapsId("addressTypeId")
    @JoinColumn(name = "address_type_id")
    private AddressType addressType;

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