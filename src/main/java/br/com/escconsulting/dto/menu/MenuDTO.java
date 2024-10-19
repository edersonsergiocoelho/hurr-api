package br.com.escconsulting.dto.menu;

import br.com.escconsulting.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private UUID menuId;
    private String name;
    private String description;
    private String icon;
    private UUID menuParentId;
    private MenuDTO menuParent;
    private String url;
    private Integer menuOrder;
    private List<MenuDTO> subMenus;

    public MenuDTO(Menu menu) {
        this.menuId = menu.getMenuId();
        this.name = menu.getName();
        this.icon = menu.getIcon();
        this.menuParentId = menu.getMenuParent() != null ? menu.getMenuParent().getMenuId() : null;
        this.url = menu.getUrl();
        this.menuOrder = menu.getMenuOrder();
        this.subMenus = menu.getSubMenus() != null ? menu.getSubMenus().stream().map(MenuDTO::new).collect(Collectors.toList()) : null;
    }
}