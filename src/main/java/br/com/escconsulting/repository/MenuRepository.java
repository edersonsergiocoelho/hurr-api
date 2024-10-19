package br.com.escconsulting.repository;

import br.com.escconsulting.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {

    Optional<Menu> findById(UUID menuId);

    @Query("SELECT m FROM Menu m " +
           "JOIN RoleMenu rm ON m.menuId = rm.menu.menuId " +
           "LEFT JOIN FETCH m.subMenus " +
           "WHERE rm.role.roleId IN :roleIds " +
           "AND rm.typeMenu.typeMenuName = :typeMenuName")
    List<Menu> findByRoleIdsAndTypeMenu(@Param("roleIds") Set<UUID> roleIds, @Param("typeMenuName") String typeMenuName);
}