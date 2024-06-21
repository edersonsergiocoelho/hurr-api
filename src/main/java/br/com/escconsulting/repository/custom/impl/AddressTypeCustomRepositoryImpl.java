package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.address.type.AddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressType;
import br.com.escconsulting.repository.AddressTypeRepository;
import br.com.escconsulting.repository.custom.AddressTypeCustomRepository;
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
public class AddressTypeCustomRepositoryImpl extends SimpleJpaRepository<AddressType, UUID> implements AddressTypeCustomRepository {

    private final EntityManager entityManager;

    private final AddressTypeRepository addressTypeRepository;

    public AddressTypeCustomRepositoryImpl(EntityManager entityManager, AddressTypeRepository addressTypeRepository) {
        super(AddressType.class, entityManager);
        this.entityManager = entityManager;
        this.addressTypeRepository = addressTypeRepository;
    }

    public Page<AddressType> searchPage(AddressTypeSearchDTO addressTypeSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AddressType> cq = cb.createQuery(AddressType.class);
        Root<AddressType> root = cq.from(AddressType.class);

        Predicate spec = cb.conjunction();

        if (addressTypeSearchDTO != null) {

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

        TypedQuery<AddressType> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AddressType> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, this.countSearchPage(addressTypeSearchDTO));
    }

    public Long countSearchPage(AddressTypeSearchDTO addressTypeSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AddressType> root = cq.from(AddressType.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (addressTypeSearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}