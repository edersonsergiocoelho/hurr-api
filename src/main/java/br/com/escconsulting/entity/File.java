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
 * Classe que representa um arquivo.
 * <p>
 * Esta classe mapeia a tabela "file" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true, of = "fileId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class File extends AbstractEntity implements Serializable {

    /**
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = -2073879407182934779L;

    /**
     * Identificador único do arquivo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private UUID fileId;

    /**
     * Tipo de conteúdo do arquivo (MIME type).
     */
    @Column(name = "content_type", length = 50, nullable = false)
    private String contentType;

    /**
     * Nome original do arquivo.
     */
    @Column(name = "original_file_name", length = 1000, nullable = false)
    private String originalFileName;

    /**
     * Dados do arquivo em formato de array de bytes.
     */
    @Column(name = "data_as_byte_array", nullable = false)
    private byte[] dataAsByteArray;

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