package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserCustomRepository extends JpaRepository<User, UUID> {

    Page<User> searchPage(UserSearchDTO userSearchDTO, Pageable pageable);

    Long countSearchPage(UserSearchDTO userSearchDTO);
}