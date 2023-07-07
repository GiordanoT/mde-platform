package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import it.disim.univaq.sose.project.feign.EPackageMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.UserMicroServiceFeignClient;
import it.disim.univaq.sose.project.model.EClass;
import it.disim.univaq.sose.project.repository.EClassRepository;

@Service
public class EClassServiceImpl implements EClassService {
	
	@Autowired
	protected EClassRepository repository;
	
	@Autowired
	private EPackageMicroServiceFeignClient packageService;
	
	@Autowired
	private UserMicroServiceFeignClient userService;

	@Override
	public List<EClass> findAll() { return repository.findAll(); }

	@Override
	public Optional<EClass> findById(Long id) { return repository.findById(id); }
	
	@Override
	public EClass[] findByPackageId(Long id) { return repository.findByPackageId(id); }

	@Override
	public boolean create(EClass obj) { 
		// Checking if the package  exist.
		JsonNode json = packageService.findById(obj.getPackageId());
		if(json.isNull()) return false;
		// Checking if the user exist.
		json = userService.findById(obj.getUserId());
		if(json.isNull()) return false;
		repository.save(obj);
		return true;
	}

	@Override
	public boolean update(EClass obj) { 
		// Checking if the package exist.
		JsonNode json = packageService.findById(obj.getPackageId());
		if(json.isNull()) return false;
		// Checking if the class exist.
		Optional<EClass> check = repository.findById(obj.getId());
		if(!check.isPresent()) return false;
		// Checking if the user exist.
		json = userService.findById(obj.getUserId());
		if(json.isNull()) return false;
		repository.save(obj);
		return true;
	}

	@Override
	public boolean delete(Long id) { 
		Optional<EClass> check = repository.findById(id);
		if(!check.isPresent()) return false;
		repository.deleteById(id);
		return true;
	}

}
