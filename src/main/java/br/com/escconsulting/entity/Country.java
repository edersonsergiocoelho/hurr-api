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
@Table(name = "country")
public class Country extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 3110939456718046104L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "country_id", updatable = false, nullable = false)
    private UUID countryId;

    @Column(name = "country_name", length = 100, nullable = false, unique = true)
    private String countryName;

    @Column(name = "service_available", nullable = false)
    private Boolean serviceAvailable;
}