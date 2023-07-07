package it.disim.univaq.sose.project.controller;

import java.util.ArrayList;

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
import it.disim.univaq.sose.project.model.EObject;
import it.disim.univaq.sose.project.model.EValue;
import it.disim.univaq.sose.project.service.M1ProsumerService;

@RestController
@RequestMapping("/model")
public class M1ProsumerController {

	@Autowired
	private M1ProsumerService service;
	
	/* USER'S OBJECT ENTITIES */
	@Operation(description = "Retrieve user's model data.",
			responses = {
		       @ApiResponse(
		          description = "EObject[]",
		          content = @Content(schema = @Schema(implementation = EObject.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("user/{id}")
	public ResponseEntity<ArrayList<EObject>> getModel(@PathVariable("id") Long id) {
		ArrayList<EObject> eObjects = service.findModel(id);
		return new ResponseEntity<ArrayList<EObject>>(eObjects, HttpStatus.OK);
	}
	

	/* ONE OBJECT ENTITY */
	@Operation(description = "Retrieve object's data.",
			responses = {
		       @ApiResponse(
		          description = "EObject|null",
		          content = @Content(schema = @Schema(implementation = EObject.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/object/{id}")
	public ResponseEntity<EObject> getObjectById(@PathVariable("id") Long id) {
		EObject eObject = service.findObject(id);
		if(eObject == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<EObject>(eObject, HttpStatus.OK);
	}
	
	/* ONE VALUE ENTITY */
	@Operation(description = "Retrieve value's data.",
			responses = {
		       @ApiResponse(
		          description = "EValue|null",
		          content = @Content(schema = @Schema(implementation = EValue.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/value/{id}")
	public ResponseEntity<EValue> getValueById(@PathVariable("id") Long id) {
		EValue eValue = service.findValue(id);
		if(eValue == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<EValue>(eValue, HttpStatus.OK);
	}
	
}
