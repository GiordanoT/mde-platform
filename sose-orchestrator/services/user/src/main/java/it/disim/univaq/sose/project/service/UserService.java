package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import it.disim.univaq.sose.project.model.User;

public interface UserService {
	List<User> findAll();
	Optional<User> findById(Long id);
	Long create(User obj);
	boolean update(User obj);
	boolean delete(Long id);
	Long auth(User obj);
}
