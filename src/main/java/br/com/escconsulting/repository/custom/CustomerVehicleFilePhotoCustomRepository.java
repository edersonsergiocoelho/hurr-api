package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoDTO;
import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleFilePhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerVehicleFilePhotoCustomRepository extends JpaRepository<CustomerVehicleFilePhoto, UUID> {

    Page<CustomerVehicleFilePhotoDTO> searchPage(CustomerVehicleFilePhotoSearchDTO customerVehicleFilePhotoSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerVehicleFilePhotoSearchDTO customerVehicleFilePhotoSearchDTO);
}