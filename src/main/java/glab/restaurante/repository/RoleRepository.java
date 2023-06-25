package glab.restaurante.repository;


import glab.restaurante.modelos.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
