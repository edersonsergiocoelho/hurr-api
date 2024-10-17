package br.com.escconsulting.service.inter;

import br.com.escconsulting.dto.inter.InterIncluirPIXDTO;
import inter.banking.model.RespostaIncluirPix;

import java.util.Optional;

public interface InterPIXService {

    Optional<RespostaIncluirPix> incluirPix(InterIncluirPIXDTO interIncluirPIXDTO);
}