package br.com.escconsulting.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
