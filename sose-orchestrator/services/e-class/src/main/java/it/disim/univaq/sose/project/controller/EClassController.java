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
import it.disim.univaq.sose.project.model.EClass;
import it.disim.univaq.sose.project.service.EClassService;

@RestController
@RequestMapping("/class")
public class EClassController {

	@Autowired
	private EClassService service;
	
	
	/* ALL ENTITIES */
	@Operation(description = "Retrieve all classes.",
			responses = {
		       @ApiResponse(
		          description = "EClass[]",
        		  content = @Content(array = @ArraySchema(schema = @Schema(implementation = EClass.class))))
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("")
	public ResponseEntity<List<EClass>> getAll() {
		return new ResponseEntity<List<EClass>>(service.findAll(), HttpStatus.OK);
	}

	
	/* ONE ENTITY */
	@Operation(description = "Retrieve one class.",
			responses = {
		       @ApiResponse(
		          description = "EClass|null",
		          content = @Content(schema = @Schema(implementation = EClass.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<EClass>> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}

	
	/* CREATE ENTITY */
	@Operation(description = "Create a class.",
			responses = {
		       @ApiResponse(
		          description = "Boolean",
		          content = @Content(schema = @Schema(implementation = Boolean.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("")
	public ResponseEntity<Void> create(@RequestBody EClass obj) {
		boolean check = service.create(obj);
		if(check) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	
	/* EDIT ENTITY */
	@Operation(description = "Edit a class.",
			responses = {
		       @ApiResponse(
		          description = "Boolean",
		          content = @Content(schema = @Schema(implementation = Boolean.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("")
	public ResponseEntity<Void> update(@RequestBody EClass obj) {
		boolean check = service.update(obj);
		if(check) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	
	/* DELETE ENTITY */
	@Operation(description = "Delete a class.",
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
	
	/* ENTITIES BY Package */
	@Operation(description = "Retrieve package's classes.",
			responses = {
		       @ApiResponse(
		          description = "EClass[]",
		          content = @Content(array = @ArraySchema(schema = @Schema(implementation = EClass.class))))
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/package/{id}")
	public ResponseEntity<EClass[]> getByPackage(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findByPackageId(id), HttpStatus.OK);
	}
	
}
