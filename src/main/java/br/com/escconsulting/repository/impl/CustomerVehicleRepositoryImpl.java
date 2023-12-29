package br.com.escconsulting.repository.impl;

import br.com.escconsulting.entity.*;
import br.com.escconsulting.repository.CustomerVehicleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;
import java.util.UUID;

public class CustomerVehicleRepositoryImpl extends SimpleJpaRepository<CustomerVehicle, UUID> implements CustomerVehicleRepository {

    private final EntityManager entityManager;

    public CustomerVehicleRepositoryImpl(EntityManager entityManager) {
        super(CustomerVehicle.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<CustomerVehicle> findById(UUID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicle> cq = cb.createQuery(CustomerVehicle.class);
        Root<CustomerVehicle> root = cq.from(CustomerVehicle.class);

        Fetch<CustomerVehicle, Vehicle> vehicleFetch = root.fetch("vehicle");
        Fetch<Vehicle, VehicleBrand> vehicleBrandFetch = vehicleFetch.fetch("vehicleBrand");

        Fetch<CustomerVehicle, VehicleModel> vehicleModelFetch = root.fetch("vehicleModel");
        Fetch<VehicleModel, VehicleCategory> vehicleCategoryFetch = vehicleModelFetch.fetch("vehicleCategory");

        root.fetch("vehicleColor");
        root.fetch("vehicleFuelType");
        root.fetch("vehicleTransmission");

        Fetch<CustomerVehicle, CustomerVehicleAddress> customerVehicleAddressFetch = root.fetch("addresses");
        Fetch<CustomerVehicleAddress, Address> addressFetch = customerVehicleAddressFetch.fetch("address");

        addressFetch.fetch("country");
        addressFetch.fetch("city");
        addressFetch.fetch("state");

        Predicate spec = cb.conjunction();

        spec = cb.and(spec, cb.equal(root.get("customerVehicleId"), id));

        cq.where(spec);

        return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
    }
}