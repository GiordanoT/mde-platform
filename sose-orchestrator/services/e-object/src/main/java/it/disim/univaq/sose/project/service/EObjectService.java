package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;

import it.disim.univaq.sose.project.model.EObject;

public interface EObjectService {
	List<EObject> findAll();
	Optional<EObject> findById(Long id);
	EObject[] findByUserId(Long id);
	EObject[] findByInstanceOf(Long id);
	boolean create(EObject obj);
	boolean update(EObject obj);
	boolean delete(Long id);
	boolean conforms(Long id);
}
