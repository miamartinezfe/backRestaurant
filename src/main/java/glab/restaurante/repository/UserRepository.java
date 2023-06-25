package glab.restaurante.repository;

import glab.restaurante.modelos.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}
