package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "addressId")
@ToString
@Table(name = "address")
public class Address extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -3373295098868142075L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "address_id", updatable = false, nullable = false)
    private UUID addressId;

    @Column(name = "nickname", length = 100)
    private String nickname;

    @Column(name = "street_address", length = 300, nullable = false)
    private String streetAddress;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "complement", length = 100)
    private String complement;

    @Column(name = "zip_code", length = 20)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "address")
    private Set<AddressAddressType> addressTypes;
}