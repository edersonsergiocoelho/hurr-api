package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.state.StateSearchDTO;
import br.com.escconsulting.entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateCustomRepository extends JpaRepository<State, UUID> {

    Page<State> searchPage(StateSearchDTO stateSearchDTO, Pageable pageable);

    Long countSearchPage(StateSearchDTO stateSearchDTO);
}