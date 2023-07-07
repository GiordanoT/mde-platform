package it.disim.univaq.sose.project.controller;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.disim.univaq.sose.project.service.AggregatorService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/aggregator")
public class AggregatorController {

	@Autowired
	private AggregatorService service;
	
	/* USER'S DATA ENTITIES */
	@Operation(description = "Retrieve user's metamodel and model data.",
			responses = {
		       @ApiResponse(
		          description = "EPackage[] & EObject[]",
		          content = @Content(schema = @Schema(implementation = JsonNode.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("user/{id}")
	public ResponseEntity<JsonNode> retrieveData(@PathVariable("id") Long id) {
		System.out.println("Retrieving Data: " + new Date());
		
		CompletableFuture<JsonNode> metamodel = service.retrieveMetamodel(id);
		CompletableFuture<JsonNode> model = service.retrieveModel(id);
		
		// Execute in parallel metamodel and model services, then combine data together.
		CompletableFuture<JsonNode> combinedData = metamodel.thenCombine(model, (jsonMetamodel, jsonModel) -> {
			ObjectMapper mapper = new ObjectMapper();
	        ObjectNode combined = mapper.createObjectNode();
	        combined.set("metamodel", (JsonNode) jsonMetamodel);
	        combined.set("model", (JsonNode) jsonModel);
        	return combined;
        });
		
		JsonNode data = combinedData.join();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
}
