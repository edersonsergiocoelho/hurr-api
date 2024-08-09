package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.menu.MenuDTO;
import br.com.escconsulting.dto.menu.MenuSearchDTO;
import br.com.escconsulting.entity.Menu;
import br.com.escconsulting.mapper.MenuMapper;
import br.com.escconsulting.repository.MenuRepository;
import br.com.escconsulting.repository.custom.MenuCustomRepository;
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
public class MenuCustomRepositoryImpl extends SimpleJpaRepository<Menu, UUID> implements MenuCustomRepository {

    private final EntityManager entityManager;

    private final MenuRepository menuRepository;

    public MenuCustomRepositoryImpl(EntityManager entityManager, MenuRepository menuRepository) {
        super(Menu.class, entityManager);
        this.entityManager = entityManager;
        this.menuRepository = menuRepository;
    }

    public Page<MenuDTO> searchPage(MenuSearchDTO menuSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Menu> cq = cb.createQuery(Menu.class);
        Root<Menu> root = cq.from(Menu.class);

        root.fetch("customerVehicleBooking", JoinType.LEFT);
        root.fetch("customer", JoinType.LEFT);
        root.fetch("customerBankAccount", JoinType.LEFT);
        root.fetch("paymentMethod", JoinType.LEFT);
        root.fetch("paymentStatus", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (menuSearchDTO != null) {
            /*
            if (menuSearchDTO.getCpf() != null && ! menuSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), menuSearchDTO.getCpf()));
            }

            if (menuSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), menuSearchDTO.getPaymentMethodId()));
            }

            if (menuSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), menuSearchDTO.getPaymentStatusId()));
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

        TypedQuery<Menu> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Menu> resultList = query.getResultList();

        List<MenuDTO> dtoList = resultList.stream()
                .map(MenuMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(menuSearchDTO));
    }

    public Long countSearchPage(MenuSearchDTO menuSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Menu> root = cq.from(Menu.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (menuSearchDTO != null) {
            /*
            if (menuSearchDTO.getCpf() != null && ! menuSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), menuSearchDTO.getCpf()));
            }

            if (menuSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), menuSearchDTO.getPaymentMethodId()));
            }

            if (menuSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), menuSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<Menu> root, Sort.Order order) {
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