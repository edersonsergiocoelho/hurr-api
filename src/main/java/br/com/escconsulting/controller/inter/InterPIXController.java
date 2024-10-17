package br.com.escconsulting.controller.inter;

import br.com.escconsulting.config.InterConfig;
import br.com.escconsulting.dto.inter.InterIncluirPIXDTO;
import br.com.escconsulting.service.inter.InterPIXService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import inter.banking.model.RespostaIncluirPix;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inter/pix")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterPIXController {

    private InterConfig interConfig;

    private final InterPIXService interPIXService;

    @PostMapping
    public ResponseEntity<?> incluirPix(@RequestBody InterIncluirPIXDTO interIncluirPIXDTO) throws MPException, MPApiException {
        return interPIXService.incluirPix(interIncluirPIXDTO)
                .map(savedBank -> ResponseEntity.status(HttpStatus.CREATED).body(savedBank))
                .orElseThrow(() -> new IllegalStateException("Failed to save pix."));
    }
}