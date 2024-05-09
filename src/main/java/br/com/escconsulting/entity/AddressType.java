package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "address_type")
public class AddressType extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 5339832110946823459L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "address_type_id", updatable = false, nullable = false)
    private UUID addressTypeId;

    @Column(name = "address_type_name", length = 100, nullable = false, unique = true)
    private String addressTypeName;

    @OneToMany(mappedBy = "addressType")
    private Set<AddressAddressType> addressTypes;
}