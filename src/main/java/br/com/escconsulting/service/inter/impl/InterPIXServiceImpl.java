package br.com.escconsulting.service.inter.impl;

import br.com.escconsulting.dto.inter.InterIncluirPIXDTO;
import br.com.escconsulting.service.CustomerVehicleWithdrawalRequestService;
import br.com.escconsulting.service.PaymentStatusService;
import br.com.escconsulting.service.inter.InterPIXService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import inter.InterSdk;
import inter.banking.model.Chave;
import inter.banking.model.Destinatario;
import inter.banking.model.Pix;
import inter.banking.model.RespostaIncluirPix;
import inter.exceptions.SdkException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterPIXServiceImpl implements InterPIXService {

    private final InterSdk interSdk;

    private final CustomerVehicleWithdrawalRequestService customerVehicleWithdrawalRequestService;

    private final PaymentStatusService paymentStatusService;

    @Transactional
    @Override
    public Optional<RespostaIncluirPix> incluirPix(InterIncluirPIXDTO interIncluirPIXDTO) {

        try {
            // Busca o customerVehicleWithdrawalRequestId usando map
            return customerVehicleWithdrawalRequestService.findById(interIncluirPIXDTO.getCustomerVehicleWithdrawalRequestId())
                .map(existingCustomerVehicleWithdrawalRequest -> {

                    // Cria os dados do destinatário e do Pix
                    Destinatario destinatario = Chave.builder()
                            .chave(interIncluirPIXDTO.getChave())
                            .build();

                    Pix pix = Pix.builder()
                            .valor(interIncluirPIXDTO.getValor())
                            .destinatario(destinatario)
                            .build();

                    // Envia o Pix e obtém a resposta
                    RespostaIncluirPix resposta = null;
                    try {
                        resposta = interSdk.banking().incluirPix(pix);
                    } catch (SdkException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Pix enviado, e2eId=" + resposta.getEndToEndId());
                    try {
                        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resposta));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                    // Atualiza o status do pagamento e a data de modificação
                    existingCustomerVehicleWithdrawalRequest.setPaymentStatus(paymentStatusService.findByPaymentStatusName("PAGO").get()); // Ajustar o status conforme necessário
                    existingCustomerVehicleWithdrawalRequest.setModifiedDate(Instant.now());

                    // Salva a entidade atualizada
                    customerVehicleWithdrawalRequestService.save(existingCustomerVehicleWithdrawalRequest);

                    return Optional.of(resposta);
                })
                .orElse(Optional.empty()); // Caso o request não seja encontrado

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}