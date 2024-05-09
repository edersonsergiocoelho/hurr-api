package br.com.escconsulting.dto.user;

import br.com.escconsulting.validator.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author eders | edersonsergiocoelho@gmail.com | Ederson Sergio Monteiro Coelho
 * Data Transfer Object (DTO) para operações relacionadas ao esquecimento de senha do usuário.
 */
@Data
@PasswordMatches
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForgotPasswordDTO {

    /**
     * E-mail do usuário.
     */
    @NotEmpty
    private String email;

    /**
     * Nova senha do usuário.
     */
    @NotEmpty
    @Size(min = 6, message = "{size.userDto.password}")
    private String password;

    /**
     * Confirmação da nova senha do usuário.
     */
    @NotEmpty
    private String matchingPassword;
}