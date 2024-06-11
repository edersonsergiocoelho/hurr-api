package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.city.CitySearchDTO;
import br.com.escconsulting.entity.City;
import br.com.escconsulting.repository.CityRepository;
import br.com.escconsulting.repository.custom.CityCustomRepository;
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
public class CityCustomRepositoryImpl extends SimpleJpaRepository<City, UUID> implements CityCustomRepository {

    private final EntityManager entityManager;

    private final CityRepository cityRepository;

    public CityCustomRepositoryImpl(EntityManager entityManager, CityRepository cityRepository) {
        super(City.class, entityManager);
        this.entityManager = entityManager;
        this.cityRepository = cityRepository;
    }

    public Page<City> searchPage(CitySearchDTO citySearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> cq = cb.createQuery(City.class);
        Root<City> root = cq.from(City.class);

        Predicate spec = cb.conjunction();

        if (citySearchDTO != null) {

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

        TypedQuery<City> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<City> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, this.countSearchPage(citySearchDTO));
    }

    public Long countSearchPage(CitySearchDTO citySearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<City> root = cq.from(City.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (citySearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}