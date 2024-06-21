package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerWithdrawalRequest;
import br.com.escconsulting.mapper.CustomerWithdrawalRequestMapper;
import br.com.escconsulting.repository.CustomerWithdrawalRequestRepository;
import br.com.escconsulting.repository.custom.CustomerWithdrawalRequestCustomRepository;
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
public class CustomerWithdrawalRequestCustomRepositoryImpl extends SimpleJpaRepository<CustomerWithdrawalRequest, UUID> implements CustomerWithdrawalRequestCustomRepository {

    private final EntityManager entityManager;

    private final CustomerWithdrawalRequestRepository customerWithdrawalRequestRepository;

    public CustomerWithdrawalRequestCustomRepositoryImpl(EntityManager entityManager, CustomerWithdrawalRequestRepository customerWithdrawalRequestRepository) {
        super(CustomerWithdrawalRequest.class, entityManager);
        this.entityManager = entityManager;
        this.customerWithdrawalRequestRepository = customerWithdrawalRequestRepository;
    }

    public Page<CustomerWithdrawalRequestDTO> searchPage(CustomerWithdrawalRequestSearchDTO customerWithdrawalRequestSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerWithdrawalRequest> cq = cb.createQuery(CustomerWithdrawalRequest.class);
        Root<CustomerWithdrawalRequest> root = cq.from(CustomerWithdrawalRequest.class);

        root.fetch("customerVehicleBooking", JoinType.LEFT);
        root.fetch("customer", JoinType.LEFT);
        root.fetch("customerBankAccount", JoinType.LEFT);
        root.fetch("paymentMethod", JoinType.LEFT);
        root.fetch("paymentStatus", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (customerWithdrawalRequestSearchDTO != null) {
            if (customerWithdrawalRequestSearchDTO.getCpf() != null && ! customerWithdrawalRequestSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerWithdrawalRequestSearchDTO.getCpf()));
            }

            if (customerWithdrawalRequestSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerWithdrawalRequestSearchDTO.getPaymentMethodId()));
            }

            if (customerWithdrawalRequestSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerWithdrawalRequestSearchDTO.getPaymentStatusId()));
            }
        }

        cq.where(spec);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = pageable.getSort().stream()
                    .map(order -> buildOrder(cb, root, order))
                    .collect(Collectors.toList());
            cq.orderBy(orders);
        }

        TypedQuery<CustomerWithdrawalRequest> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerWithdrawalRequest> resultList = query.getResultList();

        List<CustomerWithdrawalRequestDTO> dtoList = resultList.stream()
                .map(CustomerWithdrawalRequestMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerWithdrawalRequestSearchDTO));
    }

    public Long countSearchPage(CustomerWithdrawalRequestSearchDTO customerWithdrawalRequestSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerWithdrawalRequest> root = cq.from(CustomerWithdrawalRequest.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerWithdrawalRequestSearchDTO != null) {
            if (customerWithdrawalRequestSearchDTO.getCpf() != null && ! customerWithdrawalRequestSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerWithdrawalRequestSearchDTO.getCpf()));
            }

            if (customerWithdrawalRequestSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerWithdrawalRequestSearchDTO.getPaymentMethodId()));
            }

            if (customerWithdrawalRequestSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerWithdrawalRequestSearchDTO.getPaymentStatusId()));
            }
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<CustomerWithdrawalRequest> root, Sort.Order order) {
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