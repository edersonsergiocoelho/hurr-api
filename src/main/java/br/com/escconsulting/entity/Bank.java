package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade que representa um banco.
 * Esta entidade mapeia para a tabela "bank".
 *
 * @autor Ederson Sergio Monteiro Coelho
 */

@Entity
@Table(name = "bank")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "bankId", callSuper = false)
@ToString
public class Bank extends AbstractEntity implements Serializable {

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
}