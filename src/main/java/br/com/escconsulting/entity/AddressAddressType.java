package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "address_address_type")
public class AddressAddressType extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -1394465772037897566L;

    @EmbeddedId
    private AddressAddressTypeId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("addressId")
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @MapsId("addressTypeId")
    @JoinColumn(name = "address_type_id")
    private AddressType addressType;
}