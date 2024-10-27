package br.com.escconsulting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Classe que representa o ID composto da associação entre endereço e tipo de endereço.
 * <p>
 * Esta classe mapeia a chave primária composta da tabela "address_address_type" no banco de dados.
 * </p>
 * <p>
 * Autor: Ederson Sergio Monteiro Coelho
 * </p>
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressAddressTypeId implements Serializable {

    @Column(name = "address_id")
    private UUID addressId; // ID do endereço

    @Column(name = "address_type_id")
    private UUID addressTypeId; // ID do tipo de endereço
}