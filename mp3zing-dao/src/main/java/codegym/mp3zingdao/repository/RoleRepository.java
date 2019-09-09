package codegym.mp3zingdao.repository;

import codegym.mp3zingdao.entity.Role;
import codegym.mp3zingdao.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
