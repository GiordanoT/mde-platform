package it.disim.univaq.sose.project.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import it.disim.univaq.sose.project.feign.EValueMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.EObjectMicroServiceFeignClient;
import it.disim.univaq.sose.project.model.EValue;
import it.disim.univaq.sose.project.model.EObject;
import it.disim.univaq.sose.project.feign.EAttributeMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.EClassMicroServiceFeignClient;

@Service
public class M1ProsumerServiceImpl implements M1ProsumerService {
	
	@Autowired
	private EObjectMicroServiceFeignClient objectService;
	
	@Autowired
	private EValueMicroServiceFeignClient valueService;
	
	@Autowired
	private EClassMicroServiceFeignClient classService;
	
	@Autowired
	private EAttributeMicroServiceFeignClient attributeService;
	
	@Override
	public ArrayList<EObject> findModel(Long id) {
		// Creating objects list.
		ArrayList<EObject> eObjects = new ArrayList<EObject>();
		JsonNode[] jsonObjects = objectService.findByUser(id);
		for(JsonNode jsonObject: jsonObjects) {
			// Creating object.
			Long objectId = jsonObject.get("id").longValue();
			EObject eObject = this.findObject(objectId);
			eObjects.add(eObject);
		}
		return eObjects;
	}

	@Override
	public EObject findObject(Long id) {
		// Checking if object exist.
		JsonNode jsonObject = objectService.findById(id);
		if(jsonObject.isNull()) return null;
		// Retrieving object data.
		Long objectId = jsonObject.get("id").longValue();
		Long objectInstanceOf = jsonObject.get("instanceOf").longValue();
		JsonNode jsonClass = classService.findById(objectInstanceOf);
		String objectClassName = jsonClass.get("name").textValue();
		Long objectUserId = jsonObject.get("userId").longValue();
		EObject eObject = new EObject(id, objectClassName, objectUserId);
		// Creating values list.
		ArrayList<EValue> eValues = this.findValuesByObject(objectId);
		eObject.setValues(eValues);
		return eObject;
	}
	
	
	@Override
	public ArrayList<EValue> findValuesByObject(Long id) {
		ArrayList<EValue> eValues = new ArrayList<EValue>();
		JsonNode[] jsonValues = valueService.findByObjectId(id);
		for(JsonNode jsonValue: jsonValues) {
			// Creating value.
			Long valueId = jsonValue.get("id").longValue();
			EValue eValue = this.findValue(valueId);
			eValues.add(eValue);
		}
		return eValues;
	}
	
	@Override
	public EValue findValue(Long id) {
		// Checking if value exist.
		JsonNode jsonValue = valueService.findById(id);
		if(jsonValue.isNull()) return null;
		Long valueId = jsonValue.get("id").longValue();
		// Retrieving value data.
		Long valueInstanceOf = jsonValue.get("instanceOf").longValue();
		JsonNode jsonAttribute = attributeService.findById(valueInstanceOf);
		String valueAttributeName = jsonAttribute.get("name").textValue();
		String valueRaw = jsonValue.get("raw").textValue();
		Long valueUserId = jsonValue.get("userId").longValue();
		EValue eValue = new EValue(valueId, valueAttributeName, valueRaw, valueUserId);
		return eValue;
	}

}
