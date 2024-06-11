package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.dto.bank.BankSearchDTO;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.mapper.BankMapper;
import br.com.escconsulting.repository.BankRepository;
import br.com.escconsulting.repository.custom.BankCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class BankCustomRepositoryImpl extends SimpleJpaRepository<Bank, UUID> implements BankCustomRepository {

    private final EntityManager entityManager;

    private final BankRepository bankRepository;

    public BankCustomRepositoryImpl(EntityManager entityManager, BankRepository bankRepository) {
        super(Bank.class, entityManager);
        this.entityManager = entityManager;
        this.bankRepository = bankRepository;
    }

    public Page<BankDTO> searchPage(BankSearchDTO bankSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bank> cq = cb.createQuery(Bank.class);
        Root<Bank> root = cq.from(Bank.class);

        Fetch<Bank, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle");
        Fetch<CustomerVehicle, Customer> customerVehicleCustomerFetch = customerVehicleFetch.fetch("customer");

        Fetch<CustomerVehicle, Vehicle> customerVehicleVehicleFetch = customerVehicleFetch.fetch("vehicle");
        Fetch<Vehicle, VehicleBrand> vehicleVehicleBrandFetch = customerVehicleVehicleFetch.fetch("vehicleBrand");

        Fetch<CustomerVehicle, VehicleModel> customerVehicleVehicleModelFetch = customerVehicleFetch.fetch("vehicleModel");
        Fetch<VehicleModel, VehicleCategory> vehicleModelVehicleCategoryFetch = customerVehicleVehicleModelFetch.fetch("vehicleCategory");

        Fetch<CustomerVehicle, VehicleColor> customerVehicleVehicleColorFetch = customerVehicleFetch.fetch("vehicleColor");
        Fetch<CustomerVehicle, VehicleFuelType> customerVehicleVehicleFuelTypeFetch = customerVehicleFetch.fetch("vehicleFuelType");
        Fetch<CustomerVehicle, VehicleTransmission> customerVehicleVehicleTransmissionFetch = customerVehicleFetch.fetch("vehicleTransmission");

        Fetch<CustomerVehicle, CustomerVehicleAddress> customerVehicleCustomerVehicleAddressFetch = customerVehicleFetch.fetch("addresses");
        Fetch<CustomerVehicleAddress, Address> customerVehicleAddressAddressFetch = customerVehicleCustomerVehicleAddressFetch.fetch("address");

        Fetch<Address, Country> addressCountryFetch = customerVehicleAddressAddressFetch.fetch("country");
        Fetch<Address, State> addressStateFetch = customerVehicleAddressAddressFetch.fetch("state");
        Fetch<Address, City> addressCityFetch = customerVehicleAddressAddressFetch.fetch("city");

        Fetch<Bank, Customer> customerFetch = root.fetch("customer");

        Predicate spec = cb.conjunction();

        if (bankSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), bankSearchDTO.getCustomerId()));
        }

        cq.where(spec);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = pageable.getSort().stream()
                    .map(order -> {
                        String property = order.getProperty();
                        return order.isAscending() ? cb.asc(root.get(property)) : cb.desc(root.get(property));
                    })
                    .collect(Collectors.toList());

            cq.orderBy(orders);
        }

        TypedQuery<Bank> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Bank> resultList = query.getResultList();

        List<BankDTO> dtoList = resultList.stream()
                .map(BankMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(bankSearchDTO));
    }

    public Long countSearchPage(BankSearchDTO bankSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bank> root = cq.from(Bank.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (bankSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), bankSearchDTO.getCustomerId()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}