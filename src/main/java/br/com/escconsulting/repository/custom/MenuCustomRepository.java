package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.menu.MenuDTO;
import br.com.escconsulting.dto.menu.MenuSearchDTO;
import br.com.escconsulting.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuCustomRepository extends JpaRepository<Menu, UUID> {

    Page<MenuDTO> searchPage(MenuSearchDTO menuSearchDTO, Pageable pageable);

    Long countSearchPage(MenuSearchDTO menuSearchDTO);
}