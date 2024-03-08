package br.com.escconsulting.dto.mercado.pago;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonSerialize
public class MercadoPagoPreferenceItemRequestDTO {

    private final String id;
    private final String title;
    private final String description;
    private final String pictureUrl;
    private final String categoryId;
    private final Integer quantity;
    private final BigDecimal unitPrice;
    private final String currencyId;
}