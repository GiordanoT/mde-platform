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
import it.disim.univaq.sose.project.model.EAttribute;
import it.disim.univaq.sose.project.service.EAttributeService;

@RestController
@RequestMapping("/attribute")
public class EAttributeController {

	@Autowired
	private EAttributeService service;
	
	
	/* ALL ENTITIES */
	@Operation(description = "Retrieve all attributes.",
			responses = {
		       @ApiResponse(
		          description = "EAttribute[]",
        		  content = @Content(array = @ArraySchema(schema = @Schema(implementation = EAttribute.class))))
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("")
	public ResponseEntity<List<EAttribute>> getAll() {
		return new ResponseEntity<List<EAttribute>>(service.findAll(), HttpStatus.OK);
	}

	
	/* ONE ENTITY */
	@Operation(description = "Retrieve one attribute.",
			responses = {
		       @ApiResponse(
		          description = "EAttribute|null",
		          content = @Content(schema = @Schema(implementation = EAttribute.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<EAttribute>> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}

	
	/* CREATE ENTITY */
	@Operation(description = "Create an attribute.",
			responses = {
		       @ApiResponse(
		          description = "Long (attributeId)",
		          content = @Content(schema = @Schema(implementation = Boolean.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("")
	public ResponseEntity<Long> create(@RequestBody EAttribute obj) {
		Long check = service.create(obj);
		if(check > 0) return new ResponseEntity<Long>(check, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	
	/* EDIT ENTITY */
	@Operation(description = "Edit an attribute.",
			responses = {
		       @ApiResponse(
		          description = "Boolean",
		          content = @Content(schema = @Schema(implementation = Boolean.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("")
	public ResponseEntity<Void> update(@RequestBody EAttribute obj) {
		boolean check = service.update(obj);
		if(check) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	
	/* DELETE ENTITY */
	@Operation(description = "Delete an attribute.",
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
	
	/* ENTITIES BY Class */
	@Operation(description = "Retrieve class's attributes.",
			responses = {
		       @ApiResponse(
		          description = "EAttribute[]",
		          content = @Content(array = @ArraySchema(schema = @Schema(implementation = EAttribute.class)))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/class/{id}")
	public ResponseEntity<EAttribute[]> getByClass(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findByClassId(id), HttpStatus.OK);
	}
	
}
