package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.menu.MenuDTO;
import br.com.escconsulting.dto.menu.MenuSearchDTO;
import br.com.escconsulting.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuService {

    Optional<Menu> findById(UUID menuId);

    List<Menu> findAll();

    List<MenuDTO> findByTypeMenuMeAll(LocalUser localUser, String typeMenuName);

    Page<MenuDTO> searchPage(LocalUser localUser, MenuSearchDTO menuSearchDTO, Pageable pageable);

    Optional<Menu> save(Menu menu);

    Optional<Menu> update(UUID menuId, Menu menu);

    void delete(UUID menuId);
}