package br.com.escconsulting.repository.impl;

import br.com.escconsulting.dto.user.role.UserRoleSearchDTO;
import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.entity.UserRoleId;
import br.com.escconsulting.repository.UserRoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserRoleRepositoryImpl extends SimpleJpaRepository<UserRole, UserRoleId> implements UserRoleRepository {

    private final EntityManager entityManager;

    public UserRoleRepositoryImpl(EntityManager entityManager) {
        super(UserRole.class, entityManager);
        this.entityManager = entityManager;
    }

    public Page<UserRole> searchPage(UserRoleSearchDTO userRoleSearchDTO, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserRole> cq = cb.createQuery(UserRole.class);
        Root<UserRole> root = cq.from(UserRole.class);

        Fetch<UserRole, User> userFetch = root.fetch("user");
        Fetch<UserRole, Role> roleFetch = root.fetch("role");

        Predicate spec = cb.conjunction();

        if (userRoleSearchDTO != null) {
            if (userRoleSearchDTO.getDisplayName() != null && !userRoleSearchDTO.getDisplayName().isEmpty()) {
                String displayName = userRoleSearchDTO.getDisplayName().toUpperCase();
                spec = cb.and(spec, cb.like(cb.upper(root.get("user").get("displayName")), "%" + displayName + "%"));
            }

            if (userRoleSearchDTO.getEmail() != null && !userRoleSearchDTO.getEmail().isEmpty()) {
                String email = userRoleSearchDTO.getEmail().toUpperCase();
                spec = cb.and(spec, cb.like(cb.upper(root.get("user").get("email")), "%" + email + "%"));
            }

            if (userRoleSearchDTO.getRoleId() != null) {
                spec = cb.and(spec, cb.equal(root.get("role").get("roleId"), userRoleSearchDTO.getRoleId()));
            }

            if (userRoleSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("role").get("enabled"), userRoleSearchDTO.getEnabled()));
            }
        }

        cq.where(spec);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = pageable.getSort().stream()
                    .map(order -> {
                        String property = order.getProperty();
                        if (property.startsWith("user.")) {
                            String userProperty = property.substring("user.".length());
                            Join<UserRole, User> userJoin = root.join("user", JoinType.LEFT);
                            return order.isAscending() ? cb.asc(userJoin.get(userProperty)) : cb.desc(userJoin.get(userProperty));
                        } else if (property.startsWith("role.")) {
                            String roleProperty = property.substring("role.".length());
                            Join<UserRole, Role> roleJoin = root.join("role", JoinType.LEFT);
                            return order.isAscending() ? cb.asc(roleJoin.get(roleProperty)) : cb.desc(roleJoin.get(roleProperty));
                        } else {
                            return order.isAscending() ? cb.asc(root.get(property)) : cb.desc(root.get(property));
                        }
                    })
                    .collect(Collectors.toList());

            cq.orderBy(orders);
        }

        TypedQuery<UserRole> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(UserRole.class)));
        Long totalRecords = entityManager.createQuery(countQuery).getSingleResult();

        List<UserRole> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, totalRecords);
    }
}