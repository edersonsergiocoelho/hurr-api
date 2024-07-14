package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceDTO;
import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleFileInsurance;
import br.com.escconsulting.mapper.CustomerVehicleFileInsuranceMapper;
import br.com.escconsulting.repository.CustomerVehicleFileInsuranceRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleFileInsuranceCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerVehicleFileInsuranceCustomRepositoryImpl extends SimpleJpaRepository<CustomerVehicleFileInsurance, UUID> implements CustomerVehicleFileInsuranceCustomRepository {

    private final EntityManager entityManager;

    private final CustomerVehicleFileInsuranceRepository customerVehicleFileInsuranceRepository;

    public CustomerVehicleFileInsuranceCustomRepositoryImpl(EntityManager entityManager, CustomerVehicleFileInsuranceRepository customerVehicleFileInsuranceRepository) {
        super(CustomerVehicleFileInsurance.class, entityManager);
        this.entityManager = entityManager;
        this.customerVehicleFileInsuranceRepository = customerVehicleFileInsuranceRepository;
    }

    public Page<CustomerVehicleFileInsuranceDTO> searchPage(CustomerVehicleFileInsuranceSearchDTO customerVehicleFileInsuranceSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleFileInsurance> cq = cb.createQuery(CustomerVehicleFileInsurance.class);
        Root<CustomerVehicleFileInsurance> root = cq.from(CustomerVehicleFileInsurance.class);

        root.fetch("customerVehicleBooking", JoinType.LEFT);
        root.fetch("customer", JoinType.LEFT);
        root.fetch("customerBankAccount", JoinType.LEFT);
        root.fetch("paymentMethod", JoinType.LEFT);
        root.fetch("paymentStatus", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (customerVehicleFileInsuranceSearchDTO != null) {
            /*
            if (customerVehicleFileInsuranceSearchDTO.getCpf() != null && ! customerVehicleFileInsuranceSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerVehicleFileInsuranceSearchDTO.getCpf()));
            }

            if (customerVehicleFileInsuranceSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleFileInsuranceSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleFileInsuranceSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleFileInsuranceSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = pageable.getSort().stream()
                    .map(order -> buildOrder(cb, root, order))
                    .collect(Collectors.toList());
            cq.orderBy(orders);
        }

        TypedQuery<CustomerVehicleFileInsurance> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleFileInsurance> resultList = query.getResultList();

        List<CustomerVehicleFileInsuranceDTO> dtoList = resultList.stream()
                .map(CustomerVehicleFileInsuranceMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleFileInsuranceSearchDTO));
    }

    public Long countSearchPage(CustomerVehicleFileInsuranceSearchDTO customerVehicleFileInsuranceSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleFileInsurance> root = cq.from(CustomerVehicleFileInsurance.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleFileInsuranceSearchDTO != null) {
            /*
            if (customerVehicleFileInsuranceSearchDTO.getCpf() != null && ! customerVehicleFileInsuranceSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerVehicleFileInsuranceSearchDTO.getCpf()));
            }

            if (customerVehicleFileInsuranceSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleFileInsuranceSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleFileInsuranceSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleFileInsuranceSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<CustomerVehicleFileInsurance> root, Sort.Order order) {
        String property = order.getProperty();
        Path<Object> path = getPath(root, property);
        return order.isAscending() ? cb.asc(path) : cb.desc(path);
    }

    private Path<Object> getPath(From<?, ?> root, String property) {
        String[] properties = property.split("\\.");
        Path<Object> path = (Path<Object>) root;
        for (String prop : properties) {
            path = path.get(prop);
        }
        return path;
    }
}