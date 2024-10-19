package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.menu.MenuDTO;
import br.com.escconsulting.dto.menu.MenuSearchDTO;
import br.com.escconsulting.dto.type.menu.TypeMenuDTO;
import br.com.escconsulting.entity.Menu;
import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.RoleMenu;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.mapper.MenuMapper;
import br.com.escconsulting.mapper.TypeMenuMapper;
import br.com.escconsulting.repository.MenuRepository;
import br.com.escconsulting.repository.custom.MenuCustomRepository;
import br.com.escconsulting.service.MenuService;
import br.com.escconsulting.service.UserNewService;
import com.google.common.collect.Lists;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuServiceImpl implements MenuService {

    // Service's
    private final UserNewService userNewService;

    // Repository's
    private final MenuRepository menuRepository;

    private final MenuCustomRepository menuCustomRepository;

    @Transactional
    @Override
    public Optional<Menu> findById(UUID menuId) {

        return Optional.ofNullable(menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found with menuId: " + menuId)));
    }

    @Transactional
    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Transactional
    @Override
    public List<MenuDTO> findByTypeMenuMeAll(LocalUser localUser, String typeMenuName) {

        User user = userNewService.findById(localUser.getUser().getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<Role> roles = user.getRoles();
        Set<UUID> roleIds = roles.stream().map(Role::getRoleId).collect(Collectors.toSet());

        List<Menu> menus = menuRepository.findByRoleIdsAndTypeMenu(roleIds, typeMenuName);

        return buildMenuHierarchy(menus);
    }

    private List<MenuDTO> buildMenuHierarchy(List<Menu> menus) {
        Map<UUID, MenuDTO> menuMap = menus.stream()
                .collect(Collectors.toMap(Menu::getMenuId, MenuDTO::new));

        List<MenuDTO> rootMenus = menus.stream()
                .map(menu -> menuMap.get(menu.getMenuId()))
                .filter(menuDTO -> menuDTO.getMenuParentId() == null)
                .sorted(Comparator.comparing(MenuDTO::getMenuOrder)) // Sort by menu.order
                .collect(Collectors.toList());

        menus.stream()
                .map(menu -> menuMap.get(menu.getMenuId()))
                .filter(menuDTO -> menuDTO.getMenuParentId() != null)
                .forEach(menuDTO -> {
                    MenuDTO parentMenu = menuMap.get(menuDTO.getMenuParentId());
                    if (parentMenu != null) {
                        parentMenu.getSubMenus().add(menuDTO);
                    }
                });

        return rootMenus;
    }

    @Transactional
    @Override
    public Page<MenuDTO> searchPage(LocalUser localUser, MenuSearchDTO menuSearchDTO, Pageable pageable) {
        return menuCustomRepository.searchPage(menuSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<Menu> save(Menu menu) {

        menu.setCreatedDate(Instant.now());
        menu.setEnabled(Boolean.TRUE);

        return Optional.of(menuRepository.save(menu));
    }

    @Transactional
    @Override
    public Optional<Menu> update(UUID menuId, Menu menu) {
        return findById(menuId)
                .map(existingMenu -> {

                    existingMenu.setEnabled(menu.getEnabled());
                    existingMenu.setModifiedDate(Instant.now());

                    return menuRepository.save(existingMenu);
                });
    }

    @Transactional
    @Override
    public void delete(UUID menuId) {
        findById(menuId).ifPresent(menuRepository::delete);
    }
}