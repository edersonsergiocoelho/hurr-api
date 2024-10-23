package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.vehicle.brand.VehicleBrandDTO;
import br.com.escconsulting.dto.vehicle.brand.VehicleBrandSearchDTO;
import br.com.escconsulting.entity.VehicleBrand;
import br.com.escconsulting.mapper.VehicleBrandMapper;
import br.com.escconsulting.repository.VehicleBrandRepository;
import br.com.escconsulting.repository.custom.VehicleBrandCustomRepository;
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
public class VehicleBrandCustomRepositoryImpl extends SimpleJpaRepository<VehicleBrand, UUID> implements VehicleBrandCustomRepository {

    private final EntityManager entityManager;

    private final VehicleBrandRepository vehicleBrandRepository;

    public VehicleBrandCustomRepositoryImpl(EntityManager entityManager, VehicleBrandRepository vehicleBrandRepository) {
        super(VehicleBrand.class, entityManager);
        this.entityManager = entityManager;
        this.vehicleBrandRepository = vehicleBrandRepository;
    }

    public Page<VehicleBrandDTO> searchPage(VehicleBrandSearchDTO vehicleBrandSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VehicleBrand> cq = cb.createQuery(VehicleBrand.class);
        Root<VehicleBrand> root = cq.from(VehicleBrand.class);

        Predicate spec = cb.conjunction();

        if (vehicleBrandSearchDTO != null) {

            // Adiciona condição de filtro para o campo searchValue em todos os campos desejados
            if (vehicleBrandSearchDTO.getGlobalFilter() != null && !vehicleBrandSearchDTO.getGlobalFilter().isEmpty()) {
                String likePattern = "%" + vehicleBrandSearchDTO.getGlobalFilter().toUpperCase() + "%";

                Predicate namePredicate = cb.like(cb.upper(root.get("vehicleBrandName")), likePattern);
                // Adicione outros campos aqui da mesma forma
                Predicate enabledPredicate = cb.like(cb.upper(root.get("enabled").as(String.class)), likePattern);

                // Combine todos os predicados em uma disjunção (OR)
                spec = cb.and(spec, cb.or(namePredicate, enabledPredicate));
            }

            // Verifica se o campo 'vehicleBrandName' está presente e adiciona a condição com like '%...%'
            if (vehicleBrandSearchDTO.getVehicleBrandName() != null && !vehicleBrandSearchDTO.getVehicleBrandName().isEmpty()) {
                String likePattern = "%" + vehicleBrandSearchDTO.getVehicleBrandName() + "%";
                spec = cb.and(spec, cb.like(cb.upper(root.get("vehicleBrandName")), likePattern.toUpperCase()));
            }

            // Verifica se o campo 'enabled' não é nulo e adiciona a condição
            if (vehicleBrandSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), vehicleBrandSearchDTO.getEnabled()));
            }
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

        TypedQuery<VehicleBrand> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<VehicleBrand> resultList = query.getResultList();

        List<VehicleBrandDTO> dtoList = resultList.stream()
                .map(VehicleBrandMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(vehicleBrandSearchDTO));
    }

    public Long countSearchPage(VehicleBrandSearchDTO vehicleBrandSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VehicleBrand> root = cq.from(VehicleBrand.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (vehicleBrandSearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}