package it.disim.univaq.sose.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import it.disim.univaq.sose.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameAndPassword(String username, String password);
}
