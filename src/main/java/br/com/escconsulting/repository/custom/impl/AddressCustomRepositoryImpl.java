package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.address.AddressSearchDTO;
import br.com.escconsulting.entity.Address;
import br.com.escconsulting.repository.AddressRepository;
import br.com.escconsulting.repository.custom.AddressCustomRepository;
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
public class AddressCustomRepositoryImpl extends SimpleJpaRepository<Address, UUID> implements AddressCustomRepository {

    private final EntityManager entityManager;

    private final AddressRepository addressRepository;

    public AddressCustomRepositoryImpl(EntityManager entityManager, AddressRepository addressRepository) {
        super(Address.class, entityManager);
        this.entityManager = entityManager;
        this.addressRepository = addressRepository;
    }

    public Page<Address> searchPage(AddressSearchDTO addressSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> cq = cb.createQuery(Address.class);
        Root<Address> root = cq.from(Address.class);

        Predicate spec = cb.conjunction();

        if (addressSearchDTO != null) {

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

        TypedQuery<Address> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Address> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, this.countSearchPage(addressSearchDTO));
    }

    public Long countSearchPage(AddressSearchDTO addressSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Address> root = cq.from(Address.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (addressSearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}