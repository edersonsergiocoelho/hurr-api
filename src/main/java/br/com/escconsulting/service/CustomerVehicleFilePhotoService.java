package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoDTO;
import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoSearchDTO;
import br.com.escconsulting.entity.CustomerVehicle;
import br.com.escconsulting.entity.CustomerVehicleFilePhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleFilePhotoService {

    Optional<CustomerVehicleFilePhoto> findById(UUID customerVehicleFilePhotoId);

    List<CustomerVehicleFilePhoto> findByCustomerVehicle(UUID customerVehicleId);

    Optional<CustomerVehicleFilePhoto> findByCustomerVehicleAndCoverPhoto(UUID customerVehicleId);

    List<CustomerVehicleFilePhoto> findAll();

    Page<CustomerVehicleFilePhotoDTO> searchPage(LocalUser localUser, CustomerVehicleFilePhotoSearchDTO customerVehicleFilePhotoSearchDTO, Pageable pageable);

    Optional<CustomerVehicleFilePhoto> save(CustomerVehicleFilePhoto customerVehicleFilePhoto);

    List<CustomerVehicleFilePhoto> saveAll(List<CustomerVehicleFilePhoto> customerVehicleFilePhotos);

    Optional<CustomerVehicleFilePhoto> update(UUID customerVehicleFilePhotoId, CustomerVehicleFilePhoto customerVehicleFilePhoto);

    void delete(UUID customerVehicleFilePhotoId);

    void deleteByCustomerVehicle(UUID customerVehicleId);
}