package it.disim.univaq.sose.project.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import it.disim.univaq.sose.autogenerated.epackage.*;
import it.disim.univaq.sose.project.feign.UserMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.MetamodelMicroServiceFeignClient;


@Endpoint
public class EPackageEndpoint {
	
	private static final String NAMESPACE_URI = "http://disim.it/univaq/sose/autogenerated/epackage";
	
	@Autowired
	private UserMicroServiceFeignClient userService;
	
	@Autowired
	private MetamodelMicroServiceFeignClient metamodelService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPackageRequest")
	@ResponsePayload
	public GetPackageResponse getSum(@RequestPayload GetPackageRequest request) {
		GetPackageResponse response = new GetPackageResponse();
		
		// Retrieving package data.
		JsonNode jsonPackage;
		try { jsonPackage = metamodelService.findpackageById(request.getPackageId()); } 
		catch(Exception e) { return response; }
		if(jsonPackage == null) return response;
		String packageName = jsonPackage.get("name").asText();
		String packageUri = jsonPackage.get("uri").asText();
		ArrayNode packageClasses = (ArrayNode) jsonPackage.get("classes");
		int packageClassesSize = packageClasses.size();
		
		// Retrieving user data.
		Long userId = jsonPackage.get("userId").asLong();
		JsonNode user = userService.findById(userId);
		String userName = user.get("username").asText();
	
		response.setName(packageName);
		response.setCreateBy(userName);
		response.setUri(packageUri);
		if(packageClassesSize == 1) {
			response.setDescription("Inside the specified package there is 1 class.");
		} else {
			response.setDescription("Inside the specified package there are " + packageClassesSize + " classes.");
		}
		return response;
	}
	
}