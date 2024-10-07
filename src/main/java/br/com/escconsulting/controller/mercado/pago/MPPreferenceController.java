package br.com.escconsulting.controller.mercado.pago;

import br.com.escconsulting.dto.mercado.pago.MPPreferenceRequestDTO;
import br.com.escconsulting.service.mercado.pago.MPPreferenceService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mp/preference")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPPreferenceController {

    private final MPPreferenceService mpPreferenceService;

    @GetMapping("/{preferenceId}")
    public ResponseEntity<Preference> findById(@PathVariable("preferenceId") String preferenceId) throws MPException, MPApiException {
        return mpPreferenceService.findById(preferenceId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public Preference create(@RequestBody MPPreferenceRequestDTO MPPreferenceRequestDTO) throws MPException, MPApiException {
        return mpPreferenceService.createPreference(MPPreferenceRequestDTO);
    }
}