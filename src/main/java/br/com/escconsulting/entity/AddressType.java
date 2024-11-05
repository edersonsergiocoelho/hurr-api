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
import java.util.Set;
import java.util.UUID;

/**
 * Classe que representa o tipo de endereço.
 * <p>
 * Esta classe mapeia a tabela "address_type" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "addressTypeId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address_type")
public class AddressType extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 5339832110946823459L;

    /**
     * ID único para o tipo de endereço.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "address_type_id", updatable = false, nullable = false)
    private UUID addressTypeId;

    /**
     * Nome do tipo de endereço.
     */
    @Column(name = "address_type_name", length = 100, nullable = false, unique = true)
    private String addressTypeName;

    /**
     * Associações de tipos de endereço.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "addressType")
    private Set<AddressAddressType> addressTypes;

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