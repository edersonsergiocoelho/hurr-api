package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.repository.custom.UserNewCustomRepository;
import br.com.escconsulting.repository.UserRepository;
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
public class UserNewCustomRepositoryImpl extends SimpleJpaRepository<User, UUID> implements UserNewCustomRepository {

    private final EntityManager entityManager;

    private final UserRepository userRepository;

    public UserNewCustomRepositoryImpl(EntityManager entityManager, UserRepository userRepository) {
        super(User.class, entityManager);
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    public Page<User> searchPage(UserSearchDTO userSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        Predicate spec = cb.conjunction();

        if (userSearchDTO != null) {

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

        TypedQuery<User> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<User> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, this.countSearchPage(userSearchDTO));
    }

    public Long countSearchPage(UserSearchDTO userSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (userSearchDTO != null) {

        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}