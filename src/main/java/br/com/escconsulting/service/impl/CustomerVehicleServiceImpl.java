package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleSearchDTO;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.repository.CustomerVehicleRepository;
import br.com.escconsulting.service.CustomerVehicleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleServiceImpl implements CustomerVehicleService {

    private final CustomerVehicleRepository customerVehicleRepository;

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Optional<CustomerVehicle> findById(UUID customerVehicleId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicle> cq = cb.createQuery(CustomerVehicle.class);
        Root<CustomerVehicle> root = cq.from(CustomerVehicle.class);

        Fetch<CustomerVehicle, Vehicle> vehicleFetch = root.fetch("vehicle");
        Fetch<Vehicle, VehicleBrand> vehicleBrandFetch = vehicleFetch.fetch("vehicleBrand");

        Fetch<CustomerVehicle, VehicleModel> vehicleModelFetch = root.fetch("vehicleModel");
        Fetch<VehicleModel, VehicleCategory> vehicleCategoryFetch = vehicleModelFetch.fetch("vehicleCategory");

        Fetch<CustomerVehicle, VehicleColor> vehicleColorFetch = root.fetch("vehicleColor");
        Fetch<CustomerVehicle, VehicleFuelType> vehicleFuelTypeFetch = root.fetch("vehicleFuelType");
        Fetch<CustomerVehicle, VehicleTransmission> vehicleTransmissionFetch = root.fetch("vehicleTransmission");

        Fetch<CustomerVehicle, CustomerVehicleAddress> customerVehicleAddressFetch = root.fetch("addresses", JoinType.LEFT);
        Fetch<CustomerVehicleAddress, Address> addressFetch = customerVehicleAddressFetch.fetch("address", JoinType.LEFT);

        Fetch<Address, AddressAddressType> addressAddressTypeFetch = addressFetch.fetch("addressTypes", JoinType.LEFT);
        Fetch<AddressAddressType, AddressType> addressTypeFetch = addressAddressTypeFetch.fetch("addressType", JoinType.LEFT);

        Fetch<Address, Country> countryFetch = addressFetch.fetch("country", JoinType.LEFT);
        Fetch<Address, City> cityFetch = addressFetch.fetch("city", JoinType.LEFT);
        Fetch<Address, State> stateFetch = addressFetch.fetch("state", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        spec = cb.and(spec, cb.equal(root.get("customerVehicleId"), customerVehicleId));

        cq.where(spec);

        return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    @Transactional
    public List<CustomerVehicle> findAll() {
        return customerVehicleRepository.findAll();
    }

    @Override
    @Transactional
    public List<CustomerVehicle> search(CustomerVehicleSearchDTO customerVehicleSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicle> cq = cb.createQuery(CustomerVehicle.class);
        Root<CustomerVehicle> root = cq.from(CustomerVehicle.class);

        Fetch<CustomerVehicle, Vehicle> vehicleFetch = root.fetch("vehicle");
        Fetch<Vehicle, VehicleBrand> vehicleBrandFetch = vehicleFetch.fetch("vehicleBrand");

        Fetch<CustomerVehicle, VehicleModel> vehicleModelFetch = root.fetch("vehicleModel");
        Fetch<VehicleModel, VehicleCategory> vehicleCategoryFetch = vehicleModelFetch.fetch("vehicleCategory");

        Fetch<CustomerVehicle, VehicleColor> vehicleColorFetch = root.fetch("vehicleColor");
        Fetch<CustomerVehicle, VehicleFuelType> vehicleFuelTypeFetch = root.fetch("vehicleFuelType");
        Fetch<CustomerVehicle, VehicleTransmission> vehicleTransmissionFetch = root.fetch("vehicleTransmission");

        Fetch<CustomerVehicle, CustomerVehicleAddress> customerVehicleAddressFetch = root.fetch("addresses", JoinType.LEFT);
        Fetch<CustomerVehicleAddress, Address> addressFetch = customerVehicleAddressFetch.fetch("address", JoinType.LEFT);

        Fetch<Address, AddressAddressType> addressAddressTypeFetch = addressFetch.fetch("addressTypes", JoinType.LEFT);
        Fetch<AddressAddressType, AddressType> addressTypeFetch = addressAddressTypeFetch.fetch("addressType", JoinType.LEFT);

        Fetch<Address, Country> countryFetch = addressFetch.fetch("country", JoinType.LEFT);
        Fetch<Address, City> cityFetch = addressFetch.fetch("city", JoinType.LEFT);
        Fetch<Address, State> stateFetch = addressFetch.fetch("state", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        spec = cb.and(spec, cb.equal(root.get("addresses").get("address").get("addressTypes").get("addressType").get("addressTypeName"), "VEHICLE"));

        if (customerVehicleSearchDTO.getVehicleId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicle").get("vehicleId"), customerVehicleSearchDTO.getVehicleId()));
        }

        if (customerVehicleSearchDTO.getVehicleModelId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleModel").get("vehicleModelId"), customerVehicleSearchDTO.getVehicleModelId()));
        }

        if (customerVehicleSearchDTO.getVehicleBrandId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicle").get("vehicleBrand").get("vehicleBrandId"), customerVehicleSearchDTO.getVehicleBrandId()));
        }

        if (customerVehicleSearchDTO.getVehicleCategoryId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleModel").get("vehicleCategory").get("vehicleCategoryId"), customerVehicleSearchDTO.getVehicleCategoryId()));
        }

        if (customerVehicleSearchDTO.getVehicleColorId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleColor").get("vehicleColorId"), customerVehicleSearchDTO.getVehicleColorId()));
        }

        if (customerVehicleSearchDTO.getVehicleFuelTypeId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleFuelType").get("vehicleFuelTypeId"), customerVehicleSearchDTO.getVehicleFuelTypeId()));
        }

        if (customerVehicleSearchDTO.getVehicleTransmissionId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleTransmission").get("vehicleTransmissionId"), customerVehicleSearchDTO.getVehicleTransmissionId()));
        }

        if (customerVehicleSearchDTO.getCountryName() != null && !customerVehicleSearchDTO.getCountryName().isEmpty()) {
            spec = cb.and(spec, cb.equal(root.get("addresses").get("address").get("country").get("countryName"), customerVehicleSearchDTO.getCountryName()));
        }

        if (customerVehicleSearchDTO.getStateName() != null && !customerVehicleSearchDTO.getStateName().isEmpty()) {
            spec = cb.and(spec, cb.equal(root.get("addresses").get("address").get("state").get("stateName"), customerVehicleSearchDTO.getStateName()));
        }

        if (customerVehicleSearchDTO.getStateName() != null && !customerVehicleSearchDTO.getStateName().isEmpty()) {
            spec = cb.and(spec, cb.equal(root.get("addresses").get("address").get("city").get("cityName"), customerVehicleSearchDTO.getCityName()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    @Transactional
    public Optional<CustomerVehicle> save(CustomerVehicle customerVehicle) {

        customerVehicle.setCreatedDate(Instant.now());
        customerVehicle.setEnabled(Boolean.TRUE);

        return Optional.of(customerVehicleRepository.save(customerVehicle));
    }

    @Override
    @Transactional
    public Optional<CustomerVehicle> update(UUID customerVehicleId, CustomerVehicle customerVehicle) {
        return findById(customerVehicleId)
                .map(existingState -> {

                    existingState.setEnabled(customerVehicle.getEnabled());
                    existingState.setModifiedDate(Instant.now());

                    return customerVehicleRepository.save(existingState);
                });
    }

    @Override
    @Transactional
    public void delete(UUID customerVehicleId) {
        findById(customerVehicleId).ifPresent(customerVehicleRepository::delete);
    }
}