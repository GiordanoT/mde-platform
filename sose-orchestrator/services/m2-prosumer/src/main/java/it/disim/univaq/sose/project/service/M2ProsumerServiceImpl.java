package it.disim.univaq.sose.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import it.disim.univaq.sose.project.feign.EPackageMicroServiceFeignClient;
import it.disim.univaq.sose.project.model.EAttribute;
import it.disim.univaq.sose.project.model.EClass;
import it.disim.univaq.sose.project.model.EPackage;
import it.disim.univaq.sose.project.feign.EClassMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.EAttributeMicroServiceFeignClient;

@Service
public class M2ProsumerServiceImpl implements M2ProsumerService {
	
	@Autowired
	private EPackageMicroServiceFeignClient packageService;
	
	@Autowired
	private EClassMicroServiceFeignClient classService;
	
	@Autowired
	private EAttributeMicroServiceFeignClient attributeService;
	
	@Override
	public ArrayList<EPackage> findMetaModel(Long id) {
		// Creating packages list.
		ArrayList<EPackage> ePackages = new ArrayList<EPackage>();
		JsonNode[] jsonPackages = packageService.findByUser(id);
		for(JsonNode jsonPackage: jsonPackages) {
			// Creating package.
			Long packageId = jsonPackage.get("id").longValue();
			EPackage ePackage = this.findPackage(packageId);
			ePackages.add(ePackage);
		}
		return ePackages;
	}

	@Override
	public EPackage findPackage(Long id) {
		// Checking if the package exist.
		JsonNode jsonPackage = packageService.findById(id);
		if(jsonPackage.isNull()) return null;
		// Retrieving package data.
		Long packageId = jsonPackage.get("id").longValue();
		String packageName = jsonPackage.get("name").textValue();
		String packageUri = jsonPackage.get("uri").textValue();
		Long packageUserId = jsonPackage.get("userId").longValue();
		EPackage ePackage = new EPackage(packageId, packageName, packageUri, packageUserId);
		// Creating classes list.
		ArrayList<EClass> eClasses = this.findClassesByPackage(packageId);
		ePackage.setClasses(eClasses);
		return ePackage;
	}
	
	
	@Override
	public ArrayList<EClass> findClassesByPackage(Long id) {
		ArrayList<EClass> eClasses = new ArrayList<EClass>();
		JsonNode[] jsonClasses = classService.findByPackageId(id);
		for(JsonNode jsonClass: jsonClasses) {
			// Creating class.
			Long classId = jsonClass.get("id").longValue();
			EClass eClass = this.findClass(classId);
			eClasses.add(eClass);
		}
		return eClasses;
	}
	
	@Override
	public EClass findClass(Long id) {
		// Checking if the class exist.
		JsonNode jsonClass = classService.findById(id);
		if(jsonClass.isNull()) return null;
		// Retrieving class data.
		String className = jsonClass.get("name").textValue();
		Boolean classIsAbstract = jsonClass.get("isAbstract").booleanValue();
		Long classUserId = jsonClass.get("userId").longValue();
		EClass eClass = new EClass(id, className, classIsAbstract, classUserId);
		// Creating attributes list.
		ArrayList<EAttribute> eAttributes = this.findAttributesByClass(id);
		eClass.setAttributes(eAttributes);
		return eClass;
	}

	@Override
	public ArrayList<EAttribute> findAttributesByClass(Long id) {
		ArrayList<EAttribute> eAttributes = new ArrayList<EAttribute>();
		JsonNode[] jsonAttributes = attributeService.findByClassId(id);
		for(JsonNode jsonAttribute: jsonAttributes) {
			// Creating attribute.
			Long attributeId = jsonAttribute.get("id").longValue();
			EAttribute eAttribute = this.findAttribute(attributeId);
			eAttributes.add(eAttribute);
		}
		return eAttributes;
	}
	
	@Override
	public EAttribute findAttribute(Long id) {
		// Checking if the attribute exist.
		JsonNode jsonAttribute = attributeService.findById(id);
		if(jsonAttribute.isNull()) return null;
		// Retrieving attribute data.
		String attributeName = jsonAttribute.get("name").textValue();
		String attributeType = jsonAttribute.get("type").textValue();
		String attributeDefaultValue = jsonAttribute.get("defaultValue").textValue();
		Long attributeUserId = jsonAttribute.get("userId").longValue();
		EAttribute eAttribute = new EAttribute(id, attributeName, attributeType, attributeDefaultValue, attributeUserId);
		return eAttribute;
	}

}
