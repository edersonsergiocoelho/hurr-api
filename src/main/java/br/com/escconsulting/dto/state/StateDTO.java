package br.com.escconsulting.dto.state;

import br.com.escconsulting.dto.country.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {

    private UUID stateId;
    private String stateName;
    private CountryDTO country;
    private Boolean serviceAvailable;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}