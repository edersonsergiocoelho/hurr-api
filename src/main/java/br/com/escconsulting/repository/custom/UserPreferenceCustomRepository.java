package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.use.preference.UserPreferenceDTO;
import br.com.escconsulting.dto.use.preference.UserPreferenceSearchDTO;
import br.com.escconsulting.entity.UserPreference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserPreferenceCustomRepository extends JpaRepository<UserPreference, UUID> {

    Page<UserPreferenceDTO> searchPage(UserPreferenceSearchDTO userPreferenceSearchDTO, Pageable pageable);

    Long countSearchPage(UserPreferenceSearchDTO userPreferenceSearchDTO);
}