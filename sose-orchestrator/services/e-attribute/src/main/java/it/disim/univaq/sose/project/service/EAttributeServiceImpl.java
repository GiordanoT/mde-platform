package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import it.disim.univaq.sose.project.feign.EClassMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.UserMicroServiceFeignClient;
import it.disim.univaq.sose.project.model.EAttribute;
import it.disim.univaq.sose.project.repository.EAttributeRepository;

@Service
public class EAttributeServiceImpl implements EAttributeService {
	
	@Autowired
	protected EAttributeRepository repository;
	
	@Autowired
	private EClassMicroServiceFeignClient classService;
	
	@Autowired
	private UserMicroServiceFeignClient userService;

	@Override
	public List<EAttribute> findAll() { return repository.findAll(); }

	@Override
	public Optional<EAttribute> findById(Long id) { return repository.findById(id); }
	
	@Override
	public EAttribute[] findByClassId(Long id) { return repository.findByClassId(id); }

	@Override
	public Long create(EAttribute obj) { 
		// Checking if the type is one of the available ones.
		if(!EAttribute.types.contains(obj.getType())) return (long) 0;
		// Checking if the class exist.
		JsonNode json = classService.findById(obj.getClassId());
		if(json.isNull()) return (long) 0;
		// Checking if the user exist.
		json = userService.findById(obj.getUserId());
		if(json.isNull()) return (long) 0;
		repository.save(obj);
		return obj.getId();
	}

	@Override
	public boolean update(EAttribute obj) { 
		// Checking if the type is one of the available ones.
		if(!EAttribute.types.contains(obj.getType())) return false;
		// Checking if the class exist.
		JsonNode json = classService.findById(obj.getClassId());
		if(json.isNull()) return false;
		// Checking if the attribute exist.
		Optional<EAttribute> check = repository.findById(obj.getId());
		if(!check.isPresent()) return false;
		// Checking if the user exist.
		json = userService.findById(obj.getUserId());
		if(json.isNull()) return false;
		repository.save(obj);
		return true;
	}

	@Override
	public boolean delete(Long id) { 
		Optional<EAttribute> check = repository.findById(id);
		if(!check.isPresent()) return false;
		repository.deleteById(id);
		return true;
	}

}
