package br.com.escconsulting.service.mercado.pago;

import br.com.escconsulting.dto.mercado.pago.MPPreferenceRequestDTO;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

import java.util.Optional;

public interface MPPreferenceService {

    Optional<Preference> findById(String preferenceId) throws MPException, MPApiException;

    Preference createPreference(MPPreferenceRequestDTO MPPreferenceRequestDTO) throws MPException, MPApiException;
}