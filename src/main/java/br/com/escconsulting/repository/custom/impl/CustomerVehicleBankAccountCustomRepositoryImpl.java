package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountDTO;
import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountSearchDTO;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.mapper.CustomerVehicleBankAccountMapper;
import br.com.escconsulting.repository.CustomerVehicleBankAccountRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleBankAccountCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerVehicleBankAccountCustomRepositoryImpl extends SimpleJpaRepository<CustomerVehicleBankAccount, UUID> implements CustomerVehicleBankAccountCustomRepository {

    private final EntityManager entityManager;

    private final CustomerVehicleBankAccountRepository customerVehicleBankAccountRepository;

    public CustomerVehicleBankAccountCustomRepositoryImpl(EntityManager entityManager, CustomerVehicleBankAccountRepository customerVehicleBankAccountRepository) {
        super(CustomerVehicleBankAccount.class, entityManager);
        this.entityManager = entityManager;
        this.customerVehicleBankAccountRepository = customerVehicleBankAccountRepository;
    }

    public Page<CustomerVehicleBankAccountDTO> searchPage(CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleBankAccount> cq = cb.createQuery(CustomerVehicleBankAccount.class);
        Root<CustomerVehicleBankAccount> root = cq.from(CustomerVehicleBankAccount.class);

        Fetch<CustomerVehicleBankAccount, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle");
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

        Fetch<CustomerVehicleBankAccount, Customer> customerFetch = root.fetch("customer");

        Predicate spec = cb.conjunction();

        if (customerVehicleBankAccountSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), customerVehicleBankAccountSearchDTO.getCustomerId()));
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

        TypedQuery<CustomerVehicleBankAccount> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleBankAccount> resultList = query.getResultList();

        List<CustomerVehicleBankAccountDTO> dtoList = resultList.stream()
                .map(CustomerVehicleBankAccountMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleBankAccountSearchDTO));
    }

    public Page<CustomerVehicleBankAccountDTO> customerVehicleSearchPage(CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleBankAccount> cq = cb.createQuery(CustomerVehicleBankAccount.class);
        Root<CustomerVehicleBankAccount> root = cq.from(CustomerVehicleBankAccount.class);

        Fetch<CustomerVehicleBankAccount, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle");
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

        Fetch<CustomerVehicleBankAccount, Customer> customerFetch = root.fetch("customer");

        Predicate spec = cb.conjunction();

        if (customerVehicleBankAccountSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBankAccountSearchDTO.getCustomerId()));
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

        TypedQuery<CustomerVehicleBankAccount> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleBankAccount> resultList = query.getResultList();

        List<CustomerVehicleBankAccountDTO> dtoList = resultList.stream()
                .map(CustomerVehicleBankAccountMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleBankAccountSearchDTO));
    }

    public Long countSearchPage(CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleBankAccount> root = cq.from(CustomerVehicleBankAccount.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleBankAccountSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), customerVehicleBankAccountSearchDTO.getCustomerId()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    public Long countCustomerVehicleSearchPage(CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleBankAccount> root = cq.from(CustomerVehicleBankAccount.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleBankAccountSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBankAccountSearchDTO.getCustomerId()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    public BigDecimal sumCustomerVehicleTotalEarnings(CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerVehicleBankAccount> root = cq.from(CustomerVehicleBankAccount.class);

        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));

        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBankAccountSearchDTO.getCustomerId()));

        cq.where(spec);

        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }

    public BigDecimal sumCustomerVehicleWithdrawableCurrentBalance(CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerVehicleBankAccount> root = cq.from(CustomerVehicleBankAccount.class);

        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));

        spec = cb.isNotNull(root.get("bookingStartDate"));
        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBankAccountSearchDTO.getCustomerId()));

        cq.where(spec);

        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }

    public BigDecimal sumCustomerVehicleWithdrawableBalance(CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerVehicleBankAccount> root = cq.from(CustomerVehicleBankAccount.class);

        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));

        spec = cb.isNotNull(root.get("bookingStartDate"));
        spec = cb.isNotNull(root.get("bookingEndDate"));
        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBankAccountSearchDTO.getCustomerId()));

        cq.where(spec);

        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }
}