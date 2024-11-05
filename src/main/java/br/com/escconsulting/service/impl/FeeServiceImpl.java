package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.fee.FeeDTO;
import br.com.escconsulting.dto.fee.FeeSearchDTO;
import br.com.escconsulting.entity.Fee;
import br.com.escconsulting.mapper.FeeMapper;
import br.com.escconsulting.repository.FeeRepository;
import br.com.escconsulting.repository.custom.FeeCustomRepository;
import br.com.escconsulting.service.FeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    private final FeeCustomRepository feeCustomRepository;

    @Transactional
    @Override
    public Optional<Fee> findById(UUID feeId) {

        return Optional.ofNullable(feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fee not found with feeId: " + feeId)));
    }

    /*
    @Transactional
    @Override
    public Optional<Fee> findByFeeName(String feeName) {

        return Optional.ofNullable(feeRepository.findByFeeName(feeName)
                .orElseThrow(() -> new RuntimeException("Fee not found with feeName: " + feeName)));
    }
    */

    @Transactional
    @Override
    public List<Fee> findAll() {
        return feeRepository.findAll();
    }

    @Transactional
    @Override
    public Page<FeeDTO> searchPage(FeeSearchDTO feeSearchDTO, Pageable pageable) {
        return feeCustomRepository.searchPage(feeSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<Fee> save(Fee fee) {
        return Optional.of(feeRepository.save(fee));
    }

    @Transactional
    @Override
    public Optional<Fee> update(UUID feeId, Fee fee) {
        return findById(feeId)
                .map(existingFee -> {

                    FeeMapper.INSTANCE.update(fee, existingFee);

                    existingFee.setModifiedDate(Instant.now());

                    return feeRepository.save(existingFee);
                });
    }

    @Transactional
    @Override
    public void delete(UUID feeId) {
        findById(feeId).ifPresent(feeRepository::delete);
    }

    @Transactional
    @Override
    public void deleteAll(List<UUID> feeIds) {
        feeRepository.deleteAllById(feeIds);
    }
}