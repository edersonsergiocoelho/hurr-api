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
@Table(name = "city")
public class City extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -3373295098868142075L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "city_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "city_name", length = 100, nullable = false, unique = true)
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @Column(name = "service_available", nullable = false)
    private Boolean serviceAvailable;
}