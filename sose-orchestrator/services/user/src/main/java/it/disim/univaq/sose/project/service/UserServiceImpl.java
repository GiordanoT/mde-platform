package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.disim.univaq.sose.project.model.User;
import it.disim.univaq.sose.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	protected UserRepository repository;

	@Override
	public List<User> findAll() { return repository.findAll(); }

	@Override
	public Optional<User> findById(Long id) { return repository.findById(id); }

	@Override
	public Long create(User obj) { 
		// Checking if the username already exist.
		Optional<User> check = repository.findByUsername(obj.getUsername());
		if(check.isPresent()) return (long) 0;
		repository.save(obj);
		return obj.getId();
	}

	@Override
	public boolean update(User obj) { 
		// Checking if the user exist.
		Optional<User> check = repository.findById(obj.getId());
		if(!check.isPresent()) return false;
		// Checking if the username already exist.
		check = repository.findByUsername(obj.getUsername());
		if(check.isPresent()) return false;
		repository.save(obj); 
		return true;
	}

	@Override
	public boolean delete(Long id) { 
		Optional<User> check = repository.findById(id);
		if(!check.isPresent()) return false;
		repository.deleteById(id);
		return true;
	}
	
	@Override
	public Long auth(User obj) { 
		Optional<User> check = repository.findByUsernameAndPassword(obj.getUsername(), obj.getPassword());
		if(!check.isPresent()) return (long) 0;
		return check.get().getId();
	}
	
}
