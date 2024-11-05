package br.com.escconsulting.entity;

import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
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
 * Classe que representa a aprovação de um arquivo.
 * <p>
 * Esta classe mapeia a tabela "file_approved" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "fileApprovedId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file_approved")
public class FileApproved extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 5731193894818810497L;

    /**
     * Identificador único da aprovação do arquivo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_approved_id")
    private UUID fileApprovedId;

    /**
     * Identificador do arquivo associado.
     */
    @Column(name = "file_id", nullable = false)
    private UUID fileId;

    /**
     * Identificador do usuário que aprovou o arquivo.
     */
    @Column(name = "approved_by")
    private UUID approvedBy;

    /**
     * Identificador do usuário que reprovou o arquivo.
     */
    @Column(name = "reproved_by")
    private UUID reprovedBy;

    /**
     * Mensagem de aprovação ou reprovação.
     */
    @Column(name = "message", nullable = false)
    private String message;

    /**
     * Tabela do arquivo.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "file_table", length = 100, nullable = false)
    private FileTable fileTable;

    /**
     * Identificador do cliente associado.
     */
    @Column(name = "customer_id")
    private UUID customerId;

    /**
     * Identificador do usuário associado.
     */
    @Column(name = "user_id")
    private UUID userId;

    /**
     * Tipo de arquivo.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "file_type", length = 100, nullable = false)
    private FileType fileType;

    /**
     * Identificador do usuário que criou o registro.
     */
    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    /**
     * Identificador do usuário que modificou o registro.
     */
    @Column(name = "modified_by")
    private UUID modifiedBy;

    /**
     * Cliente associado.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    /**
     * Usuário associado.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

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