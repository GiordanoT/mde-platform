package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import it.disim.univaq.sose.project.feign.EAttributeMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.EObjectMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.UserMicroServiceFeignClient;
import it.disim.univaq.sose.project.model.EValue;
import it.disim.univaq.sose.project.repository.EValueRepository;

@Service
public class EValueServiceImpl implements EValueService {
	
	@Autowired
	protected EValueRepository repository;
	
	@Autowired
	private EAttributeMicroServiceFeignClient attributeService;
	
	@Autowired
	private EObjectMicroServiceFeignClient objectService;
	
	@Autowired
	private UserMicroServiceFeignClient userService;

	@Override
	public List<EValue> findAll() { return repository.findAll(); }

	@Override
	public Optional<EValue> findById(Long id) { return repository.findById(id); }
	
	@Override
	public EValue[] findByObjectId(Long id) { return repository.findByObjectId(id); }

	@Override
	public boolean create(EValue obj) { 
		// Checking if the instanceOf  exist.
		JsonNode json = attributeService.findById(obj.getInstanceOf());
		if(json.isNull()) return false;
		// Setting raw value equals to the default value..
		String defaultValue = json.get("defaultValue").textValue();
		obj.setRaw(defaultValue);		
		// Checking if the object  exist.
		json = objectService.findById(obj.getObjectId());
		if(json.isNull()) return false;
		// Checking if the user exist.
		json = userService.findById(obj.getUserId());
		if(json.isNull()) return false;
		repository.save(obj);
		return true;
	}

	@Override
	public boolean update(EValue obj) { 
		// Checking if the instanceOf exist.
		JsonNode json = attributeService.findById(obj.getInstanceOf());
		if(json.isNull()) return false;
		// Checking if the object exist.
		json = objectService.findById(obj.getObjectId());
		if(json.isNull()) return false;
		// Checking if the value exist.
		Optional<EValue> check = repository.findById(obj.getId());
		if(!check.isPresent()) return false;
		// Checking if the user exist.
		json = userService.findById(obj.getUserId());
		if(json.isNull()) return false;
		repository.save(obj);
		return true;
	}

	@Override
	public boolean delete(Long id) { 
		Optional<EValue> check = repository.findById(id);
		if(!check.isPresent()) return false;
		repository.deleteById(id);
		return true;
	}

}