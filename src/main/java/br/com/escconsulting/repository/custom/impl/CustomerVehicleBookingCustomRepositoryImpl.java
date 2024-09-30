package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingSearchDTO;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.mapper.CustomerVehicleBookingMapper;
import br.com.escconsulting.repository.CustomerVehicleBookingRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleBookingCustomRepository;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerVehicleBookingCustomRepositoryImpl extends SimpleJpaRepository<CustomerVehicleBooking, UUID> implements CustomerVehicleBookingCustomRepository {

    private final EntityManager entityManager;

    private final CustomerVehicleBookingRepository customerVehicleBookingRepository;

    public CustomerVehicleBookingCustomRepositoryImpl(EntityManager entityManager, CustomerVehicleBookingRepository customerVehicleBookingRepository) {
        super(CustomerVehicleBooking.class, entityManager);
        this.entityManager = entityManager;
        this.customerVehicleBookingRepository = customerVehicleBookingRepository;
    }

    @Override
    public Optional<CustomerVehicleBooking> findById(UUID customerVehicleBookingId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleBooking> cq = cb.createQuery(CustomerVehicleBooking.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);

        // Realize o fetch de customerVehicle uma única vez e reutilize-o
        Fetch<CustomerVehicleBooking, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle", JoinType.LEFT);

        customerVehicleFetch.fetch("customer", JoinType.LEFT);
        customerVehicleFetch.fetch("vehicle", JoinType.LEFT)
                .fetch("vehicleBrand", JoinType.LEFT);

        customerVehicleFetch.fetch("vehicleModel", JoinType.LEFT)
                .fetch("vehicleCategory", JoinType.LEFT);

        customerVehicleFetch.fetch("vehicleColor", JoinType.LEFT);
        customerVehicleFetch.fetch("vehicleFuelType", JoinType.LEFT);
        customerVehicleFetch.fetch("vehicleTransmission", JoinType.LEFT);

        // Fetch de endereços e detalhes associados
        Fetch<CustomerVehicle, CustomerVehicleAddress> addressesFetch = customerVehicleFetch.fetch("addresses", JoinType.LEFT);
        Fetch<CustomerVehicleAddress, Address> addressFetch = addressesFetch.fetch("address", JoinType.LEFT);

        addressFetch.fetch("country", JoinType.LEFT);
        addressFetch.fetch("state", JoinType.LEFT);
        addressFetch.fetch("city", JoinType.LEFT);

        // Fetch do customer diretamente no root
        root.fetch("customer", JoinType.LEFT);

        // Fetch da FK customerAddressBilling e seus relacionamentos (Address -> country, state, city)
        Fetch<CustomerVehicleBooking, CustomerAddress> billingAddressFetch = root.fetch("customerAddressBilling", JoinType.LEFT);
        Fetch<CustomerAddress, Address> billingAddress = billingAddressFetch.fetch("address", JoinType.LEFT);
        billingAddress.fetch("country", JoinType.LEFT);
        billingAddress.fetch("state", JoinType.LEFT);
        billingAddress.fetch("city", JoinType.LEFT);

        // Fetch da FK customerAddressDelivery e seus relacionamentos (Address -> country, state, city)
        Fetch<CustomerVehicleBooking, CustomerAddress> deliveryAddressFetch = root.fetch("customerAddressDelivery", JoinType.LEFT);
        Fetch<CustomerAddress, Address> deliveryAddress = deliveryAddressFetch.fetch("address", JoinType.LEFT);
        deliveryAddress.fetch("country", JoinType.LEFT);
        deliveryAddress.fetch("state", JoinType.LEFT);
        deliveryAddress.fetch("city", JoinType.LEFT);

        // Fetch da FK customerAddressPickUp e seus relacionamentos (Address -> country, state, city)
        Fetch<CustomerVehicleBooking, CustomerAddress> pickUpAddressFetch = root.fetch("customerAddressPickUp", JoinType.LEFT);
        Fetch<CustomerAddress, Address> pickUpAddress = pickUpAddressFetch.fetch("address", JoinType.LEFT);
        pickUpAddress.fetch("country", JoinType.LEFT);
        pickUpAddress.fetch("state", JoinType.LEFT);
        pickUpAddress.fetch("city", JoinType.LEFT);

        // Defina a condição para o ID
        cq.select(root).where(cb.equal(root.get("customerVehicleBookingId"), customerVehicleBookingId));

        // Execute a consulta e retorne o resultado
        return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public Optional<CustomerVehicleBooking> findByPaymentId(Long mercadoPagoPaymentId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleBooking> cq = cb.createQuery(CustomerVehicleBooking.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);

        // Realize o fetch de customerVehicle uma única vez e reutilize-o
        Fetch<CustomerVehicleBooking, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle", JoinType.LEFT);

        customerVehicleFetch.fetch("customer", JoinType.LEFT);
        customerVehicleFetch.fetch("vehicle", JoinType.LEFT)
                .fetch("vehicleBrand", JoinType.LEFT);

        customerVehicleFetch.fetch("vehicleModel", JoinType.LEFT)
                .fetch("vehicleCategory", JoinType.LEFT);

        customerVehicleFetch.fetch("vehicleColor", JoinType.LEFT);
        customerVehicleFetch.fetch("vehicleFuelType", JoinType.LEFT);
        customerVehicleFetch.fetch("vehicleTransmission", JoinType.LEFT);

        // Fetch de endereços e detalhes associados
        Fetch<CustomerVehicle, CustomerVehicleAddress> addressesFetch = customerVehicleFetch.fetch("addresses", JoinType.LEFT);
        Fetch<CustomerVehicleAddress, Address> addressFetch = addressesFetch.fetch("address", JoinType.LEFT);

        addressFetch.fetch("country", JoinType.LEFT);
        addressFetch.fetch("state", JoinType.LEFT);
        addressFetch.fetch("city", JoinType.LEFT);

        // Fetch do customer diretamente no root
        root.fetch("customer", JoinType.LEFT);

        // Fetch da FK customerAddressBilling e seus relacionamentos (Address -> country, state, city)
        Fetch<CustomerVehicleBooking, CustomerAddress> billingAddressFetch = root.fetch("customerAddressBilling", JoinType.LEFT);
        Fetch<CustomerAddress, Address> billingAddress = billingAddressFetch.fetch("address", JoinType.LEFT);
        billingAddress.fetch("country", JoinType.LEFT);
        billingAddress.fetch("state", JoinType.LEFT);
        billingAddress.fetch("city", JoinType.LEFT);

        // Fetch da FK customerAddressDelivery e seus relacionamentos (Address -> country, state, city)
        Fetch<CustomerVehicleBooking, CustomerAddress> deliveryAddressFetch = root.fetch("customerAddressDelivery", JoinType.LEFT);
        Fetch<CustomerAddress, Address> deliveryAddress = deliveryAddressFetch.fetch("address", JoinType.LEFT);
        deliveryAddress.fetch("country", JoinType.LEFT);
        deliveryAddress.fetch("state", JoinType.LEFT);
        deliveryAddress.fetch("city", JoinType.LEFT);

        // Fetch da FK customerAddressPickUp e seus relacionamentos (Address -> country, state, city)
        Fetch<CustomerVehicleBooking, CustomerAddress> pickUpAddressFetch = root.fetch("customerAddressPickUp", JoinType.LEFT);
        Fetch<CustomerAddress, Address> pickUpAddress = pickUpAddressFetch.fetch("address", JoinType.LEFT);
        pickUpAddress.fetch("country", JoinType.LEFT);
        pickUpAddress.fetch("state", JoinType.LEFT);
        pickUpAddress.fetch("city", JoinType.LEFT);

        // Defina a condição para o ID
        cq.select(root).where(cb.equal(root.get("mercadoPagoPaymentId"), mercadoPagoPaymentId));

        // Execute a consulta e retorne o resultado
        return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public List<CustomerVehicleBooking> findByCustomerVehicleWithdrawableBalance(UUID customerId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleBooking> cq = cb.createQuery(CustomerVehicleBooking.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);

        Fetch<CustomerVehicleBooking, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle");
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

        Fetch<CustomerVehicleBooking, Customer> customerFetch = root.fetch("customer");

        cq.select(root);

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));
        spec = cb.and(spec, cb.isNotNull(root.get("bookingStartDate")));
        spec = cb.and(spec, cb.isNotNull(root.get("bookingEndDate")));
        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerId));

        cq.where(spec);

        return entityManager.createQuery(cq).getResultList();
    }

    public Page<CustomerVehicleBookingDTO> searchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleBooking> cq = cb.createQuery(CustomerVehicleBooking.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);

        Fetch<CustomerVehicleBooking, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle");
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

        Fetch<CustomerVehicleBooking, Customer> customerFetch = root.fetch("customer");

        Predicate spec = cb.conjunction();

        if (customerVehicleBookingSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), customerVehicleBookingSearchDTO.getCustomerId()));
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

        TypedQuery<CustomerVehicleBooking> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleBooking> resultList = query.getResultList();

        List<CustomerVehicleBookingDTO> dtoList = resultList.stream()
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleBookingSearchDTO));
    }

    public Page<CustomerVehicleBookingDTO> customerVehicleSearchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleBooking> cq = cb.createQuery(CustomerVehicleBooking.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);

        Fetch<CustomerVehicleBooking, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle");
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

        Fetch<CustomerVehicleBooking, Customer> customerFetch = root.fetch("customer");

        Predicate spec = cb.conjunction();

        if (customerVehicleBookingSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBookingSearchDTO.getCustomerId()));
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

        TypedQuery<CustomerVehicleBooking> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleBooking> resultList = query.getResultList();

        List<CustomerVehicleBookingDTO> dtoList = resultList.stream()
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countCustomerVehicleSearchPage(customerVehicleBookingSearchDTO));
    }

    public Long countSearchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleBookingSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), customerVehicleBookingSearchDTO.getCustomerId()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    public Long countCustomerVehicleSearchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleBookingSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBookingSearchDTO.getCustomerId()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    public BigDecimal sumCustomerVehicleTotalEarnings(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);

        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));

        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBookingSearchDTO.getCustomerId()));

        cq.where(spec);

        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }

    public BigDecimal sumCustomerVehicleWithdrawableCurrentBalance(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);

        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));

        spec = cb.isNotNull(root.get("bookingStartDate"));
        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBookingSearchDTO.getCustomerId()));

        cq.where(spec);

        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }

    public BigDecimal sumCustomerVehicleWithdrawableBalance(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);

        // Main query selection
        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        // Main query predicates
        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));
        spec = cb.and(spec, cb.isNotNull(root.get("bookingStartDate")));
        spec = cb.and(spec, cb.isNotNull(root.get("bookingEndDate")));
        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBookingSearchDTO.getCustomerId()));

        // Subquery to exclude CustomerVehicleBookings with a corresponding CustomerWithdrawalRequest
        Subquery<CustomerWithdrawalRequest> subquery = cq.subquery(CustomerWithdrawalRequest.class);
        Root<CustomerWithdrawalRequest> subRoot = subquery.from(CustomerWithdrawalRequest.class);

        // Subquery conditions
        Predicate subqueryCondition = cb.equal(subRoot.get("customerVehicleBooking").get("customerVehicleBookingId"), root.get("customerVehicleBookingId"));
        subquery.select(subRoot).where(subqueryCondition);

        // Ensure that there is no corresponding CustomerWithdrawalRequest
        spec = cb.and(spec, cb.not(cb.exists(subquery)));

        // Set where clause
        cq.where(spec);

        // Execute query and handle null result
        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }

    public BigDecimal sumCustomerVehicleWithdrawableBalanceUnpaid(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<CustomerVehicleBooking> root = cq.from(CustomerVehicleBooking.class);

        // Main query selection
        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        // Main query predicates
        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));
        spec = cb.and(spec, cb.isNotNull(root.get("bookingStartDate")));
        spec = cb.and(spec, cb.isNotNull(root.get("bookingEndDate")));
        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBookingSearchDTO.getCustomerId()));

        // Subquery to exclude CustomerVehicleBookings with a corresponding CustomerWithdrawalRequest
        Subquery<CustomerWithdrawalRequest> subquery = cq.subquery(CustomerWithdrawalRequest.class);
        Root<CustomerWithdrawalRequest> subRoot = subquery.from(CustomerWithdrawalRequest.class);

        // Subquery conditions
        Predicate subqueryCondition = cb.equal(subRoot.get("customerVehicleBooking").get("customerVehicleBookingId"), root.get("customerVehicleBookingId"));
        subqueryCondition = cb.equal(subRoot.get("paymentStatus").get("paymentStatusName"), "UNPAID");
        subquery.select(subRoot).where(subqueryCondition);

        // Ensure that there is no corresponding CustomerWithdrawalRequest
        spec = cb.and(spec, cb.exists(subquery));

        // Set where clause
        cq.where(spec);

        // Execute query and handle null result
        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }

    public BigDecimal withdrawableBalance(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {
        BigDecimal sum1 = sumCustomerVehicleWithdrawableBalance(customerVehicleBookingSearchDTO);
        BigDecimal sum2 = sumCustomerVehicleWithdrawableBalanceUnpaid(customerVehicleBookingSearchDTO);

        return sum1.add(sum2);
    }
}