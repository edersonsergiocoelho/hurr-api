package br.com.escconsulting.dto.mercado.pago;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MPPreferenceItemRequest {

    private final String id;
    private final String title;
    private final String description;
    private final String pictureUrl;
    private final String categoryId;
    private final Integer quantity;
    private final BigDecimal unitPrice;
    private final String currencyId;
}