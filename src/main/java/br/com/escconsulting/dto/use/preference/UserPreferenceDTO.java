package br.com.escconsulting.dto.use.preference;

import br.com.escconsulting.dto.user.UserDTO;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UserPreferenceDTO {

    private UUID userPreferenceId;
    private UserDTO user;
    private String language;
    private String theme;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}