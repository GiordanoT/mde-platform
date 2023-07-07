package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import it.disim.univaq.sose.project.feign.UserMicroServiceFeignClient;
import it.disim.univaq.sose.project.model.EPackage;
import it.disim.univaq.sose.project.repository.EPackageRepository;

@Service
public class EPackageServiceImpl implements EPackageService {
	
	@Autowired
	protected EPackageRepository repository;
	
	@Autowired
	private UserMicroServiceFeignClient userService;

	@Override
	public List<EPackage> findAll() { return repository.findAll(); }

	@Override
	public Optional<EPackage> findById(Long id) { return repository.findById(id); }
	
	@Override
	public EPackage[] findByUserId(Long id) { return repository.findByUserId(id); }

	@Override
	public boolean create(EPackage obj) { 
		// Checking if the user exist.
		JsonNode json = userService.findById(obj.getUserId());
		if(json.isNull()) return false;
		repository.save(obj);
		return true;
	}

	@Override
	public boolean update(EPackage obj) { 
		// Checking if the user exist.
		JsonNode json = userService.findById(obj.getUserId());
		if(json.isNull()) return false;
		// Checking if the package exist.
		Optional<EPackage> check = repository.findById(obj.getId());
		if(!check.isPresent()) return false;
		repository.save(obj);
		return true;
	}

	@Override
	public boolean delete(Long id) { 
		Optional<EPackage> check = repository.findById(id);
		if(!check.isPresent()) return false;
		repository.deleteById(id);
		return true;
	}

}
