package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "type_menu")
public class TypeMenu extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 3110939456718046104L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "type_menu_id", updatable = false, nullable = false)
    private UUID typeMenuId;

    @Column(name = "type_menu_name", nullable = false, unique = true, length = 100)
    private String typeMenuName;

    @Column(name = "description", nullable = false)
    private String description;
}