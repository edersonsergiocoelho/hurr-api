package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.dto.payment.method.PaymentMethodSearchDTO;
import br.com.escconsulting.entity.PaymentMethod;
import br.com.escconsulting.mapper.PaymentMethodMapper;
import br.com.escconsulting.repository.PaymentMethodRepository;
import br.com.escconsulting.repository.custom.PaymentMethodCustomRepository;
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
public class PaymentMethodCustomRepositoryImpl extends SimpleJpaRepository<PaymentMethod, UUID> implements PaymentMethodCustomRepository {

    private final EntityManager entityManager;

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodCustomRepositoryImpl(EntityManager entityManager, PaymentMethodRepository paymentMethodRepository) {
        super(PaymentMethod.class, entityManager);
        this.entityManager = entityManager;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public Page<PaymentMethodDTO> searchPage(PaymentMethodSearchDTO paymentMethodSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentMethod> cq = cb.createQuery(PaymentMethod.class);
        Root<PaymentMethod> root = cq.from(PaymentMethod.class);

        Predicate spec = cb.conjunction();

        if (paymentMethodSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), paymentMethodSearchDTO.getCustomerId()));
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

        TypedQuery<PaymentMethod> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<PaymentMethod> resultList = query.getResultList();

        List<PaymentMethodDTO> dtoList = resultList.stream()
                .map(PaymentMethodMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(paymentMethodSearchDTO));
    }

    public Long countSearchPage(PaymentMethodSearchDTO paymentMethodSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PaymentMethod> root = cq.from(PaymentMethod.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (paymentMethodSearchDTO != null) {
            spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), paymentMethodSearchDTO.getCustomerId()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}