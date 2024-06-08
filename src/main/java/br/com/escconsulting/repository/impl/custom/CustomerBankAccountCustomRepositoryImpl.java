package br.com.escconsulting.repository.impl.custom;

import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountDTO;
import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountSearchDTO;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.mapper.CustomerBankAccountMapper;
import br.com.escconsulting.repository.CustomerBankAccountRepository;
import br.com.escconsulting.repository.custom.CustomerBankAccountCustomRepository;
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
public class CustomerBankAccountCustomRepositoryImpl extends SimpleJpaRepository<CustomerBankAccount, UUID> implements CustomerBankAccountCustomRepository {

    private final EntityManager entityManager;

    private final CustomerBankAccountRepository customerBankAccountRepository;

    public CustomerBankAccountCustomRepositoryImpl(EntityManager entityManager, CustomerBankAccountRepository customerBankAccountRepository) {
        super(CustomerBankAccount.class, entityManager);
        this.entityManager = entityManager;
        this.customerBankAccountRepository = customerBankAccountRepository;
    }

    public Page<CustomerBankAccountDTO> searchPage(CustomerBankAccountSearchDTO customerBankAccountSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerBankAccount> cq = cb.createQuery(CustomerBankAccount.class);
        Root<CustomerBankAccount> root = cq.from(CustomerBankAccount.class);

        Fetch<CustomerBankAccount, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle");
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

        Fetch<CustomerBankAccount, Customer> customerFetch = root.fetch("customer");

        Predicate spec = cb.conjunction();

        if (customerBankAccountSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), customerBankAccountSearchDTO.getCustomerId()));
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

        TypedQuery<CustomerBankAccount> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerBankAccount> resultList = query.getResultList();

        List<CustomerBankAccountDTO> dtoList = resultList.stream()
                .map(CustomerBankAccountMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerBankAccountSearchDTO));
    }

    public Page<CustomerBankAccountDTO> customerVehicleSearchPage(CustomerBankAccountSearchDTO customerBankAccountSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerBankAccount> cq = cb.createQuery(CustomerBankAccount.class);
        Root<CustomerBankAccount> root = cq.from(CustomerBankAccount.class);

        Fetch<CustomerBankAccount, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle");
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

        Fetch<CustomerBankAccount, Customer> customerFetch = root.fetch("customer");

        Predicate spec = cb.conjunction();

        if (customerBankAccountSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerBankAccountSearchDTO.getCustomerId()));
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

        TypedQuery<CustomerBankAccount> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerBankAccount> resultList = query.getResultList();

        List<CustomerBankAccountDTO> dtoList = resultList.stream()
                .map(CustomerBankAccountMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerBankAccountSearchDTO));
    }

    public Long countSearchPage(CustomerBankAccountSearchDTO customerBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerBankAccount> root = cq.from(CustomerBankAccount.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerBankAccountSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), customerBankAccountSearchDTO.getCustomerId()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    public Long countCustomerVehicleSearchPage(CustomerBankAccountSearchDTO customerBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerBankAccount> root = cq.from(CustomerBankAccount.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerBankAccountSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerBankAccountSearchDTO.getCustomerId()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    public BigDecimal sumCustomerVehicleTotalEarnings(CustomerBankAccountSearchDTO customerBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerBankAccount> root = cq.from(CustomerBankAccount.class);

        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));

        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerBankAccountSearchDTO.getCustomerId()));

        cq.where(spec);

        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }

    public BigDecimal sumCustomerVehicleWithdrawableCurrentBalance(CustomerBankAccountSearchDTO customerBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerBankAccount> root = cq.from(CustomerBankAccount.class);

        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));

        spec = cb.isNotNull(root.get("bookingStartDate"));
        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerBankAccountSearchDTO.getCustomerId()));

        cq.where(spec);

        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }

    public BigDecimal sumCustomerVehicleWithdrawableBalance(CustomerBankAccountSearchDTO customerBankAccountSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerBankAccount> root = cq.from(CustomerBankAccount.class);

        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));

        spec = cb.isNotNull(root.get("bookingStartDate"));
        spec = cb.isNotNull(root.get("bookingEndDate"));
        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerBankAccountSearchDTO.getCustomerId()));

        cq.where(spec);

        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }
}