package br.com.escconsulting.dto.city;

import br.com.escconsulting.dto.state.StateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private UUID id;
    private String cityName;
    private StateDTO state;
    private Boolean serviceAvailable;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}