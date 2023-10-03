package br.com.escconsulting.repositories;

import br.com.escconsulting.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends CrudRepository<User, String> {

    UserDetails findByLogin(String login);
}
