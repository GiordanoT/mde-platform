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
// import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.disim.univaq.sose.project.model.User;
import it.disim.univaq.sose.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	
	/* ALL ENTITIES */
	@Operation(description = "Retrieve all users.",
			responses = {
		       @ApiResponse(
		          description = "User[]",
        		  content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("")
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<List<User>>(service.findAll(), HttpStatus.OK);
	}

	
	/* ONE ENTITY */
	@Operation(description = "Retrieve one user.",
			responses = {
		       @ApiResponse(
		          description = "User|null",
		          content = @Content(schema = @Schema(implementation = User.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<User>> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}

	
	/* CREATE ENTITY */
	@Operation(description = "Create a user.",
			responses = {
		       @ApiResponse(
		          description = "Long (userId)",
		          content = @Content(schema = @Schema(implementation = Boolean.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("")
	public ResponseEntity<Long> create(@RequestBody User obj) {
		Long check = service.create(obj);
		if(check > 0) return new ResponseEntity<Long>(check, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	
	/* EDIT ENTITY */
	@Operation(description = "Edit a user.",
			responses = {
		       @ApiResponse(
		          description = "Boolean",
		          content = @Content(schema = @Schema(implementation = Boolean.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("")
	public ResponseEntity<Void> update(@RequestBody User obj) {
		boolean check = service.update(obj);
		if(check) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	
	/* DELETE ENTITY */
	@Operation(description = "Delete a user.",
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
	
	

	/* AUTH */
	@Operation(description = "Authenticate a user.",
				responses = {
			       @ApiResponse(
			          description = "Long (userId)",
			          content = @Content(schema = @Schema(implementation = Boolean.class)))
			    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/auth")
	public ResponseEntity<Long> auth(@RequestBody User obj) {
		Long auth = service.auth(obj);
		if(auth > 0) return new ResponseEntity<Long>(auth, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
}
