package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * Classe que representa um banco.
 * <p>
 * Esta classe mapeia a tabela "bank".
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "bankId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank")
public class Bank extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -3438573685165419941L;

    /**
     * Identificador único do banco.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "bank_id", updatable = false, nullable = false)
    private UUID bankId;

    /**
     * Código do banco, por exemplo, "001" para Banco do Brasil.
     */
    @Column(name = "bank_code", length = 20, nullable = false)
    private String bankCode;

    /**
     * Nome do banco, por exemplo, "Banco do Brasil".
     */
    @Column(name = "bank_name", length = 100, nullable = false)
    private String bankName;

    /**
     * Identificador do arquivo associado ao banco.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    /**
     * Método de callback executado antes da persistência da entidade.
     */
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