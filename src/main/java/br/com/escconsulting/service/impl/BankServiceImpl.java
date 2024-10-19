package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.dto.bank.BankSearchDTO;
import br.com.escconsulting.entity.Bank;
import br.com.escconsulting.entity.File;
import br.com.escconsulting.entity.Bank;
import br.com.escconsulting.mapper.BankMapper;
import br.com.escconsulting.repository.BankRepository;
import br.com.escconsulting.repository.custom.BankCustomRepository;
import br.com.escconsulting.service.BankService;
import br.com.escconsulting.service.FileService;
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
public class BankServiceImpl implements BankService {

    private final FileService fileService;
    
    private final BankRepository bankRepository;

    private final BankCustomRepository bankCustomRepository;

    @Transactional
    @Override
    public Optional<Bank> findById(UUID bankId) {

        return Optional.ofNullable(bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found with bankId: " + bankId)));
    }

    @Transactional
    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Transactional
    @Override
    public Page<BankDTO> searchPage(BankSearchDTO bankSearchDTO, Pageable pageable) {
        return bankCustomRepository.searchPage(bankSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<Bank> save(Bank bank) {

        if (bank.getFile() != null) {

            Optional<File> optionalFile = fileService.save(bank.getFile());

            optionalFile.ifPresent(bank::setFile);
        }

        return Optional.of(bankRepository.save(bank));
    }

    @Transactional
    @Override
    public Optional<Bank> update(UUID bankId, Bank bank) {
        return findById(bankId)
                .map(existingBank -> {

                    if (bank.getFile() != null) {

                        if (existingBank.getFile() != null && existingBank.getFile().getFileId() != null) {

                            if (bank.getFile().getFileId() == null && existingBank.getFile().getFileId() != null) {

                                fileService.delete(existingBank.getFile().getFileId());
                            }
                        }

                        Optional<File> optionalFile = fileService.save(bank.getFile());

                        optionalFile.ifPresent(existingBank::setFile);
                    }

                    BankMapper.INSTANCE.update(bank, existingBank);

                    existingBank.setModifiedDate(Instant.now());

                    return bankRepository.save(existingBank);
                });
    }

    @Transactional
    @Override
    public void delete(UUID bankId) {
        findById(bankId).ifPresent(bankRepository::delete);
    }
}