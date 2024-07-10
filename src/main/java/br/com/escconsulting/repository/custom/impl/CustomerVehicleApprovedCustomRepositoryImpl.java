package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedDTO;
import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleApproved;
import br.com.escconsulting.mapper.CustomerVehicleApprovedMapper;
import br.com.escconsulting.repository.CustomerVehicleApprovedRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleApprovedCustomRepository;
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
public class CustomerVehicleApprovedCustomRepositoryImpl extends SimpleJpaRepository<CustomerVehicleApproved, UUID> implements CustomerVehicleApprovedCustomRepository {

    private final EntityManager entityManager;

    private final CustomerVehicleApprovedRepository customerVehicleApprovedRepository;

    public CustomerVehicleApprovedCustomRepositoryImpl(EntityManager entityManager, CustomerVehicleApprovedRepository customerVehicleApprovedRepository) {
        super(CustomerVehicleApproved.class, entityManager);
        this.entityManager = entityManager;
        this.customerVehicleApprovedRepository = customerVehicleApprovedRepository;
    }

    public Page<CustomerVehicleApprovedDTO> searchPage(CustomerVehicleApprovedSearchDTO customerVehicleApprovedSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleApproved> cq = cb.createQuery(CustomerVehicleApproved.class);
        Root<CustomerVehicleApproved> root = cq.from(CustomerVehicleApproved.class);

        root.fetch("customerVehicleBooking", JoinType.LEFT);
        root.fetch("customer", JoinType.LEFT);
        root.fetch("customerBankAccount", JoinType.LEFT);
        root.fetch("paymentMethod", JoinType.LEFT);
        root.fetch("paymentStatus", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (customerVehicleApprovedSearchDTO != null) {
            /*
            if (customerVehicleApprovedSearchDTO.getCpf() != null && ! customerVehicleApprovedSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerVehicleApprovedSearchDTO.getCpf()));
            }

            if (customerVehicleApprovedSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleApprovedSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleApprovedSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleApprovedSearchDTO.getPaymentStatusId()));
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

        TypedQuery<CustomerVehicleApproved> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleApproved> resultList = query.getResultList();

        List<CustomerVehicleApprovedDTO> dtoList = resultList.stream()
                .map(CustomerVehicleApprovedMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleApprovedSearchDTO));
    }

    public Long countSearchPage(CustomerVehicleApprovedSearchDTO customerVehicleApprovedSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleApproved> root = cq.from(CustomerVehicleApproved.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleApprovedSearchDTO != null) {
            /*
            if (customerVehicleApprovedSearchDTO.getCpf() != null && ! customerVehicleApprovedSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerVehicleApprovedSearchDTO.getCpf()));
            }

            if (customerVehicleApprovedSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleApprovedSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleApprovedSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleApprovedSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<CustomerVehicleApproved> root, Sort.Order order) {
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