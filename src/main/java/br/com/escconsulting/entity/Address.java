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
import java.util.Set;
import java.util.UUID;

/**
 * Classe que representa o endereço.
 * <p>
 * Esta classe mapeia a tabela "address" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "addressId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -3373295098868142075L;

    /**
     * ID único para o endereço.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "address_id", updatable = false, nullable = false)
    private UUID addressId;

    /**
     * Apelido do endereço.
     */
    @Column(name = "nickname", length = 100)
    private String nickname;

    /**
     * Endereço (Rua, Avenida, etc.).
     */
    @Column(name = "street_address", length = 300, nullable = false)
    private String streetAddress;

    /**
     * Número do endereço.
     */
    @Column(name = "number", nullable = false)
    private Integer number;

    /**
     * Complemento (Apartamento, Bloco, etc.).
     */
    @Column(name = "complement", length = 100)
    private String complement;

    /**
     * Código Postal.
     */
    @Column(name = "zip_code", length = 20)
    private String zipCode;

    /**
     * País.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    /**
     * Cidade.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    /**
     * Estado.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    /**
     * Tipos de endereço associados.
     */
    @OneToMany(mappedBy = "address")
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