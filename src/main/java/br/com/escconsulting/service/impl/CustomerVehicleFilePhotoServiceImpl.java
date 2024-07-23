package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoDTO;
import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleFilePhoto;
import br.com.escconsulting.repository.CustomerVehicleFilePhotoRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleFilePhotoCustomRepository;
import br.com.escconsulting.service.CustomerVehicleFilePhotoService;
import br.com.escconsulting.service.EmailService;
import br.com.escconsulting.service.PaymentStatusService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleFilePhotoServiceImpl implements CustomerVehicleFilePhotoService {

    // Service's
    private final EmailService emailService;

    private final PaymentStatusService paymentStatusService;

    // Repository's
    private final CustomerVehicleFilePhotoRepository customerVehicleFilePhotoRepository;

    private final CustomerVehicleFilePhotoCustomRepository customerVehicleFilePhotoCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerVehicleFilePhoto> findById(UUID customerVehicleFilePhotoId) {

        return Optional.ofNullable(customerVehicleFilePhotoRepository.findById(customerVehicleFilePhotoId)
                .orElseThrow(() -> new RuntimeException("CustomerVehicleFilePhoto not found with customerVehicleFilePhotoId: " + customerVehicleFilePhotoId)));
    }

    @Transactional
    @Override
    public List<CustomerVehicleFilePhoto> findByCustomerVehicle(UUID customerVehicleId) {
        return customerVehicleFilePhotoRepository.findByCustomerVehicle(customerVehicleId);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleFilePhoto> findByCustomerVehicleAndCoverPhoto(UUID customerVehicleId) {
        return customerVehicleFilePhotoRepository.findByCustomerVehicleAndCoverPhoto(customerVehicleId);
    }

    @Transactional
    @Override
    public List<CustomerVehicleFilePhoto> findAll() {
        return customerVehicleFilePhotoRepository.findAll();
    }

    @Transactional
    @Override
    public Page<CustomerVehicleFilePhotoDTO> searchPage(LocalUser localUser, CustomerVehicleFilePhotoSearchDTO customerVehicleFilePhotoSearchDTO, Pageable pageable) {
        return customerVehicleFilePhotoCustomRepository.searchPage(customerVehicleFilePhotoSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleFilePhoto> save(CustomerVehicleFilePhoto customerVehicleFilePhoto) {

        customerVehicleFilePhoto.setCreatedDate(Instant.now());
        customerVehicleFilePhoto.setEnabled(Boolean.TRUE);

        return Optional.of(customerVehicleFilePhotoRepository.save(customerVehicleFilePhoto));
    }

    @Transactional
    @Override
    public List<CustomerVehicleFilePhoto> saveAll(List<CustomerVehicleFilePhoto> customerVehicleFilePhotos) {

        customerVehicleFilePhotos.forEach(customerVehicleFilePhoto -> {

            customerVehicleFilePhoto.setCreatedDate(Instant.now());
            customerVehicleFilePhoto.setEnabled(Boolean.TRUE);
        });

        return customerVehicleFilePhotoRepository.saveAll(customerVehicleFilePhotos);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleFilePhoto> update(UUID customerVehicleFilePhotoId, CustomerVehicleFilePhoto customerVehicleFilePhoto) {
        return findById(customerVehicleFilePhotoId)
                .map(existingCustomerVehicleFilePhoto -> {

                    existingCustomerVehicleFilePhoto.setEnabled(customerVehicleFilePhoto.getEnabled());
                    existingCustomerVehicleFilePhoto.setModifiedDate(Instant.now());

                    return customerVehicleFilePhotoRepository.save(existingCustomerVehicleFilePhoto);
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerVehicleFilePhotoId) {
        findById(customerVehicleFilePhotoId).ifPresent(customerVehicleFilePhotoRepository::delete);
    }
}