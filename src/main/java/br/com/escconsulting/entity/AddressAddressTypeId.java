package br.com.escconsulting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AddressAddressTypeId implements Serializable {

    @Column(name = "address_id")
    private UUID addressId;

    @Column(name = "address_type_id")
    private UUID addressTypeId;
}