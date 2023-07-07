package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import it.disim.univaq.sose.project.feign.EAttributeMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.EClassMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.UserMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.EValueMicroServiceFeignClient;
import it.disim.univaq.sose.project.model.EObject;
import it.disim.univaq.sose.project.repository.EObjectRepository;

@Service
public class EObjectServiceImpl implements EObjectService {
	
	@Autowired
	protected EObjectRepository repository;
	
	@Autowired
	private EClassMicroServiceFeignClient classService;
	
	@Autowired
	private EAttributeMicroServiceFeignClient attributeService;
	
	@Autowired
	private EValueMicroServiceFeignClient valueService;
	
	@Autowired
	private UserMicroServiceFeignClient userService;

	@Override
	public List<EObject> findAll() { return repository.findAll(); }

	@Override
	public Optional<EObject> findById(Long id) { return repository.findById(id); }
	
	@Override
	public EObject[] findByUserId(Long id) { return repository.findByUserId(id); }
	
	@Override
	public EObject[] findByInstanceOf(Long id) { return repository.findByInstanceOf(id); }

	@Override
	public boolean create(EObject obj) { 
		// Checking if the user exist.
		JsonNode json = userService.findById(obj.getUserId());
		if(json.isNull()) return false;
		// Checking if the instanceOf exist.
		json = classService.findById(obj.getInstanceOf());
		if(json.isNull()) return false;
		repository.save(obj);
		// Creating the object's values.
		JsonNode[] jsonAttributes = attributeService.findByClassId(obj.getInstanceOf());
		for(JsonNode jsonAttribute: jsonAttributes) {
	        ObjectMapper mapper = new ObjectMapper();
	        ObjectNode jsonValue = mapper.createObjectNode();
	        // InstanceOf
	        Long attributeId = jsonAttribute.get("id").longValue();
	        jsonValue.put("instanceOf", attributeId);
	        // ObjectID
	        jsonValue.put("objectId", obj.getId());
	        // DefaultValue
	        String attributeDefaultValue = jsonAttribute.get("defaultValue").textValue();
	        jsonValue.put("raw", attributeDefaultValue);
	        // UserID
	        jsonValue.put("userId", obj.getUserId());
	        valueService.create(jsonValue);
		}
		return true;
	}

	@Override
	public boolean update(EObject obj) { 
		// Checking if the instanceOf exist.
		JsonNode json = classService.findById(obj.getInstanceOf());
		if(json.isNull()) return false;
		// Checking if the object exist.
		Optional<EObject> check = repository.findById(obj.getId());
		if(!check.isPresent()) return false;
		// Checking if the user exist.
		json = userService.findById(obj.getUserId());
		if(json.isNull()) return false;
		repository.save(obj);
		return true;
	}
	
	
	@Override
	public boolean delete(Long id) { 
		Optional<EObject> check = repository.findById(id);
		if(!check.isPresent()) return false;
		repository.deleteById(id);
		return true;
	}
	
	@Override
	public boolean conforms(Long id) {
		// Checking if the objects exist.
		Optional<EObject> check = repository.findById(id);
		if(!check.isPresent()) return false;
		// Checking if the instanceOf is abstract.
		JsonNode jsonClass = classService.findById(check.get().getInstanceOf());
		if(jsonClass.get("isAbstract").booleanValue()) return false;
		// Retrieving value's data.
		JsonNode[] jsonValues = valueService.findByObjectId(id);
		for(JsonNode jsonValue: jsonValues) {
			// Retrieving instanceOf (EAttribute) data.
			Long instanceOf = jsonValue.get("instanceOf").longValue();
			JsonNode jsonAttribute = attributeService.findById(instanceOf);
			String attributeType = jsonAttribute.get("type").textValue();
			String valueRaw = jsonValue.get("raw").textValue();
			// Checking if the raw value is conform to the instanceOf (EAttribute) type.
			switch(attributeType) {
				case "Boolean":
					if(!valueRaw.equalsIgnoreCase("True") && !valueRaw.equalsIgnoreCase("False")) return false;
					break;
				case "Number":
				    try { Double.parseDouble(valueRaw); } 
				    catch (NumberFormatException e) { return false; }
				    break;
				case "Char":
					if(valueRaw.length() > 1) return false;
					break;
				case "String":
					break;
				default: return false;
			}
		}
		return true;
	}

}