package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.vehicle.brand.VehicleBrandDTO;
import br.com.escconsulting.dto.vehicle.brand.VehicleBrandSearchDTO;
import br.com.escconsulting.entity.File;
import br.com.escconsulting.entity.VehicleBrand;
import br.com.escconsulting.mapper.VehicleBrandMapper;
import br.com.escconsulting.repository.VehicleBrandRepository;
import br.com.escconsulting.repository.custom.VehicleBrandCustomRepository;
import br.com.escconsulting.service.FileService;
import br.com.escconsulting.service.VehicleBrandService;
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
public class VehicleBrandServiceImpl implements VehicleBrandService {

    private final FileService fileService;

    private final VehicleBrandRepository vehicleBrandRepository;

    private final VehicleBrandCustomRepository vehicleBrandCustomRepository;

    @Transactional
    @Override
    public Optional<VehicleBrand> findById(UUID vehicleBrandId) {

        return Optional.ofNullable(vehicleBrandRepository.findById(vehicleBrandId)
                .orElseThrow(() -> new RuntimeException("VehicleBrand not found with vehicleBrandId: " + vehicleBrandId)));
    }

    @Transactional
    @Override
    public Optional<VehicleBrand> findByVehicleBrandName(String vehicleBrandName) {

        return Optional.ofNullable(vehicleBrandRepository.findByVehicleBrandName(vehicleBrandName)
                .orElseThrow(() -> new RuntimeException("VehicleBrand not found with vehicleBrandName: " + vehicleBrandName)));
    }

    @Transactional
    @Override
    public List<VehicleBrand> findAll() {
        return vehicleBrandRepository.findAll();
    }

    @Transactional
    @Override
    public Page<VehicleBrandDTO> searchPage(VehicleBrandSearchDTO vehicleBrandSearchDTO, Pageable pageable) {
        return vehicleBrandCustomRepository.searchPage(vehicleBrandSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<VehicleBrand> save(VehicleBrand vehicleBrand) {

        if (vehicleBrand.getFile() != null) {

            Optional<File> optionalFile = fileService.save(vehicleBrand.getFile());

            optionalFile.ifPresent(vehicleBrand::setFile);
        }

        return Optional.of(vehicleBrandRepository.save(vehicleBrand));
    }

    @Transactional
    @Override
    public Optional<VehicleBrand> update(UUID vehicleBrandId, VehicleBrand vehicleBrand) {
        return findById(vehicleBrandId)
                .map(existingPaymentMethod -> {

                    if (vehicleBrand.getFile() != null) {

                        if (existingPaymentMethod.getFile() != null && existingPaymentMethod.getFile().getFileId() != null) {

                            if (vehicleBrand.getFile().getFileId() == null && existingPaymentMethod.getFile().getFileId() != null) {

                                fileService.delete(existingPaymentMethod.getFile().getFileId());
                            }
                        }

                        Optional<File> optionalFile = fileService.save(vehicleBrand.getFile());

                        optionalFile.ifPresent(existingPaymentMethod::setFile);
                    }

                    VehicleBrandMapper.INSTANCE.update(vehicleBrand, existingPaymentMethod);

                    existingPaymentMethod.setModifiedDate(Instant.now());

                    return vehicleBrandRepository.save(existingPaymentMethod);
                });
    }

    @Transactional
    @Override
    public void delete(UUID vehicleBrandId) {
        findById(vehicleBrandId).ifPresent(vehicleBrandRepository::delete);
    }

    @Transactional
    @Override
    public void deleteAll(List<UUID> vehicleBrandIds) {
        vehicleBrandRepository.deleteAllById(vehicleBrandIds);
    }
}