package br.com.escconsulting.dto.address;

import br.com.escconsulting.dto.city.CityDTO;
import br.com.escconsulting.dto.country.CountryDTO;
import br.com.escconsulting.dto.state.StateDTO;
import br.com.escconsulting.entity.AddressAddressType;
import br.com.escconsulting.entity.City;
import br.com.escconsulting.entity.Country;
import br.com.escconsulting.entity.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private UUID addressId;
    private String nickname;
    private String streetAddress;
    private Integer number;
    private String complement;
    private String zipCode;
    private CountryDTO country;
    private CityDTO city;
    private StateDTO state;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}