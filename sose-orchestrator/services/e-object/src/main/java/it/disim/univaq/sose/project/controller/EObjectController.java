package it.disim.univaq.sose.project.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.disim.univaq.sose.project.model.EObject;
import it.disim.univaq.sose.project.service.EObjectService;

@RestController
@RequestMapping("/object")
public class EObjectController {

	@Autowired
	private EObjectService service;
	
	
	/* ALL ENTITIES */
	@Operation(description = "Retrieve all objects.",
			responses = {
		       @ApiResponse(
		          description = "EObject[]",
        		  content = @Content(array = @ArraySchema(schema = @Schema(implementation = EObject.class))))
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("")
	public ResponseEntity<List<EObject>> getAll() {
		return new ResponseEntity<List<EObject>>(service.findAll(), HttpStatus.OK);
	}

	
	/* ONE ENTITY */
	@Operation(description = "Retrieve one object.",
			responses = {
		       @ApiResponse(
		          description = "EObject|null",
		          content = @Content(schema = @Schema(implementation = EObject.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<EObject>> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}

	
	/* CREATE ENTITY */
	@Operation(description = "Create an object.",
			responses = {
		       @ApiResponse(
		          description = "Boolean",
		          content = @Content(schema = @Schema(implementation = Boolean.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("")
	public ResponseEntity<Void> create(@RequestBody EObject obj) {
		boolean check = service.create(obj);
		if(check) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	
	/* EDIT ENTITY */
	@Operation(description = "Edit an object.",
			responses = {
		       @ApiResponse(
		          description = "Boolean",
		          content = @Content(schema = @Schema(implementation = Boolean.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("")
	public ResponseEntity<Void> update(@RequestBody EObject obj) {
		boolean check = service.update(obj);
		if(check) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	
	/* DELETE ENTITY */
	@Operation(description = "Delete an object.",
			responses = {
		       @ApiResponse(
		          description = "Boolean",
		          content = @Content(schema = @Schema(implementation = Boolean.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		boolean check = service.delete(id);
		if(check) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	/* ENTITIES BY INSTANCEOF */
	@Operation(description = "Retrieve class's instances.",
			responses = {
		       @ApiResponse(
		          description = "EObject[]",
		          content = @Content(array = @ArraySchema(schema = @Schema(implementation = EObject.class))))
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/instanceOf/{id}")
	public ResponseEntity<EObject[]> getByInstanceOf(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findByInstanceOf(id), HttpStatus.OK);
	}
	
	/* CHECK OBJECT CONFORMANCE */
	@Operation(description = "Check if the object is conform to his instaceOf's class.",
			responses = {
		       @ApiResponse(
		          description = "Boolean",
		          content = @Content(schema = @Schema(implementation = Boolean.class)))
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/{id}/conforms")
	public ResponseEntity<Boolean> getConforms(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.conforms(id), HttpStatus.OK);
	}
	
	// ENTITIES BY USER 
	@Operation(description = "Retrieve user's objects.",
			responses = {
		       @ApiResponse(
		          description = "EObject[]",
		          content = @Content(array = @ArraySchema(schema = @Schema(implementation = EObject.class)))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/user/{id}")
	public ResponseEntity<EObject[]> getByUser(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findByUserId(id), HttpStatus.OK);
	}
	
}
