package it.disim.univaq.sose.project.service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import it.disim.univaq.sose.project.feign.MetamodelMicroServiceFeignClient;
import it.disim.univaq.sose.project.feign.ModelMicroServiceFeignClient;

@Service
public class AggregatorServiceImpl implements AggregatorService {
	
	@Autowired
	private MetamodelMicroServiceFeignClient metamodelService;
	
	@Autowired
	private ModelMicroServiceFeignClient modelService;

	@Async
	@Override
	public CompletableFuture<JsonNode> retrieveMetamodel(Long userId) {
		// Retrieving metamodel data (sleep = 3s).
		try { Thread.sleep(3 * 1000); }
		catch(Exception e) { e.printStackTrace(); }
		System.out.println("Retrieving Metamodel Data: " + new Date());
		JsonNode metamodel = metamodelService.findByUserId(userId);
		return CompletableFuture.completedFuture(metamodel); 
	}

	@Async
	@Override
	public CompletableFuture<JsonNode> retrieveModel(Long userId) {
		// Retrieving model data (sleep = 1s).
		try { Thread.sleep(1 * 1000); }
		catch(Exception e) { e.printStackTrace(); }
		System.out.println("Retrieving Model Data: " + new Date());
		JsonNode model = modelService.findByUserId(userId);
		return CompletableFuture.completedFuture(model); 
	}

}
