package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceDTO;
import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleFileInsurance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleFileInsuranceService {

    Optional<CustomerVehicleFileInsurance> findById(UUID customerVehicleFileInsuranceId);

    List<CustomerVehicleFileInsurance> findAll();

    Page<CustomerVehicleFileInsuranceDTO> searchPage(LocalUser localUser, CustomerVehicleFileInsuranceSearchDTO customerVehicleFileInsuranceSearchDTO, Pageable pageable);

    Optional<CustomerVehicleFileInsurance> save(CustomerVehicleFileInsurance customerVehicleFileInsurance);

    List<CustomerVehicleFileInsurance> saveAll(List<CustomerVehicleFileInsurance> customerVehicleFileInsurances);

    Optional<CustomerVehicleFileInsurance> update(UUID customerVehicleFileInsuranceId, CustomerVehicleFileInsurance customerVehicleFileInsurance);

    void delete(UUID customerVehicleFileInsuranceId);
}