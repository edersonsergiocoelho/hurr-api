package br.com.escconsulting.repository.impl.custom;

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

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleBookingSearchDTO));
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

        cq.select(cb.sum(root.get("withdrawableBookingValue")));

        Predicate spec = cb.isNull(root.get("bookingCancellationDate"));

        spec = cb.isNotNull(root.get("bookingStartDate"));
        spec = cb.isNotNull(root.get("bookingEndDate"));
        spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("customerId"), customerVehicleBookingSearchDTO.getCustomerId()));

        cq.where(spec);

        BigDecimal result = entityManager.createQuery(cq).getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }
}