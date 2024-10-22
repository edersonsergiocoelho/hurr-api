package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user_preference")
public class UserPreference extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único da preferência do usuário.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "user_preference_id", updatable = false, nullable = false)
    private UUID userPreferenceId;

    /**
     * Identificador único do usuário.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Associação com a entidade "User"

    /**
     * Preferência de linguagem (ex: 'en', 'pt').
     */
    @Column(name = "language", length = 10)
    private String language;

    /**
     * Tema escolhido pelo usuário (ex: 'dark', 'light').
     */
    @Column(name = "theme", length = 50)
    private String theme;

    @PrePersist
    protected void prePersist() {
        if (this.getCreatedDate() == null) {
            this.setCreatedDate(Instant.now());
        }

        if (this.getEnabled() == null) {
            this.setEnabled(Boolean.TRUE);
        }
    }
}