package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.menu.MenuDTO;
import br.com.escconsulting.dto.menu.MenuSearchDTO;
import br.com.escconsulting.entity.Menu;
import br.com.escconsulting.mapper.MenuMapper;
import br.com.escconsulting.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{menuId}")
    public ResponseEntity<?> findById(@PathVariable("menuId") UUID menuId) {
        return menuService.findById(menuId)
                .map(MenuMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/type-menu/{typeMenuName}/me/all")
    public ResponseEntity<?> findByTypeMenuMeAll(@CurrentUser LocalUser localUser,
                                       @PathVariable("typeMenuName") String typeMenuName) {
        return ResponseEntity.ok(
                menuService.findByTypeMenuMeAll(localUser, typeMenuName));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                menuService.findAll().stream()
                        .map(MenuMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(@CurrentUser LocalUser localUser,
                                    @RequestBody MenuSearchDTO menuSearchDTO,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
                                    @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<MenuDTO> menus = menuService.searchPage(localUser, menuSearchDTO, pageable);

        return ResponseEntity.ok(menus);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Menu menu) {
        return menuService.save(menu)
                .map(MenuMapper.INSTANCE::toDTO)
                .map(savedMenu -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save menu."));
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<?> update(@PathVariable("menuId") UUID menuId,
                                    @RequestBody Menu menu) {
        return menuService.update(menuId, menu)
                .map(MenuMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update menu."));
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<?> delete(@PathVariable("menuId") UUID menuId) {
        menuService.delete(menuId);
        return ResponseEntity.noContent().build();
    }
}