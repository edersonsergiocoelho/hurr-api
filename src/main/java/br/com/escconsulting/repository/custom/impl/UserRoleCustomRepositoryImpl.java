package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.user.role.UserRoleDTO;
import br.com.escconsulting.dto.user.role.UserRoleSearchDTO;
import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.mapper.UserRoleMapper;
import br.com.escconsulting.repository.UserRoleRepository;
import br.com.escconsulting.repository.custom.UserRoleCustomRepository;
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
public class UserRoleCustomRepositoryImpl extends SimpleJpaRepository<UserRole, UUID> implements UserRoleCustomRepository {

    private final EntityManager entityManager;

    private final UserRoleRepository userRoleRepository;

    public UserRoleCustomRepositoryImpl(EntityManager entityManager, UserRoleRepository userRoleRepository) {
        super(UserRole.class, entityManager);
        this.entityManager = entityManager;
        this.userRoleRepository = userRoleRepository;
    }

    public Page<UserRoleDTO> searchPage(UserRoleSearchDTO userRoleSearchDTO, Pageable pageable) {

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
                    .map(order -> buildOrder(cb, root, order))
                    .collect(Collectors.toList());
            cq.orderBy(orders);
        }

        TypedQuery<UserRole> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<UserRole> resultList = query.getResultList();

        List<UserRoleDTO> dtoList = resultList.stream()
                .map(UserRoleMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(userRoleSearchDTO));
    }

    public Long countSearchPage(UserRoleSearchDTO userRoleSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UserRole> root = cq.from(UserRole.class);
        cq.select(cb.count(root));

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

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<UserRole> root, Sort.Order order) {
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