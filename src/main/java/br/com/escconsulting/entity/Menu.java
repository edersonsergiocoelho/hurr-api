package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "menu")
public class Menu extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 3110939456718046104L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "menuId", updatable = false, nullable = false)
    private UUID menuId;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "icon", length = 100)
    private String icon;

    @ManyToOne
    @JoinColumn(name = "menu_parent_id")
    private Menu menuParent;

    @Column(name = "url", length = 200)
    private String url;

    @Column(name = "menu_order")
    private Integer menuOrder;

    @OneToMany(mappedBy = "menuParent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> subMenus;
}