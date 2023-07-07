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
import it.disim.univaq.sose.project.model.EPackage;
import it.disim.univaq.sose.project.model.EClass;
import it.disim.univaq.sose.project.model.EAttribute;
import it.disim.univaq.sose.project.service.M2ProsumerService;

@RestController
@RequestMapping("/metamodel")
public class M2ProsumerController {

	@Autowired
	private M2ProsumerService service;
	
	/* USER'S PACKAGE ENTITIES */
	@Operation(description = "Retrieve user's metamodel data.",
			responses = {
		       @ApiResponse(
		          description = "EPackage[]",
		          content = @Content(schema = @Schema(implementation = EPackage.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("user/{id}")
	public ResponseEntity<ArrayList<EPackage>> getMetamodel(@PathVariable("id") Long id) {
		ArrayList<EPackage> ePackages = service.findMetaModel(id);
		return new ResponseEntity<ArrayList<EPackage>>(ePackages, HttpStatus.OK);
	}
	

	/* ONE PACKAGE ENTITY */
	@Operation(description = "Retrieve package's data.",
			responses = {
		       @ApiResponse(
		          description = "EPackage|null",
		          content = @Content(schema = @Schema(implementation = EPackage.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/package/{id}")
	public ResponseEntity<EPackage> getPackageById(@PathVariable("id") Long id) {
		EPackage ePackage = service.findPackage(id);
		if(ePackage == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<EPackage>(ePackage, HttpStatus.OK);
	}
	
	/* ONE CLASS ENTITY */
	@Operation(description = "Retrieve class's data.",
			responses = {
		       @ApiResponse(
		          description = "EClass|null",
		          content = @Content(schema = @Schema(implementation = EClass.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/class/{id}")
	public ResponseEntity<EClass> getClassById(@PathVariable("id") Long id) {
		EClass eClass = service.findClass(id);
		if(eClass == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<EClass>(eClass, HttpStatus.OK);
	}
	
	/* ONE ATTRIBUTE ENTITY */
	@Operation(description = "Retrieve attribute's data.",
			responses = {
		       @ApiResponse(
		          description = "EAttribute|null",
		          content = @Content(schema = @Schema(implementation = EAttribute.class))) 
		    }
	)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/attribute/{id}")
	public ResponseEntity<EAttribute> getAttributeById(@PathVariable("id") Long id) {
		EAttribute eAttribute = service.findAttribute(id);
		if(eAttribute == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<EAttribute>(eAttribute, HttpStatus.OK);
	}
	
	
}
