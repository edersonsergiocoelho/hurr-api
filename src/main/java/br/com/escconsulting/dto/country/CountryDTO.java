package br.com.escconsulting.dto.country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {

    private UUID countryId;
    private String countryName;
    private String countryCode;
    private Boolean serviceAvailable;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}