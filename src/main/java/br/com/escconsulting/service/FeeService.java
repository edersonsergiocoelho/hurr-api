package br.com.escconsulting.service;

import br.com.escconsulting.dto.fee.FeeDTO;
import br.com.escconsulting.dto.fee.FeeSearchDTO;
import br.com.escconsulting.entity.Fee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeeService {

    Optional<Fee> findById(UUID feeId);

    //Optional<Fee> findByFeeName(String feeName);

    List<Fee> findAll();

    Page<FeeDTO> searchPage(FeeSearchDTO feeSearchDTO, Pageable pageable);

    Optional<Fee> save(Fee fee);

    Optional<Fee> update(UUID feeId, Fee fee);

    void delete(UUID feeId);

    void deleteAll(List<UUID> feeIds);
}