package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import it.disim.univaq.sose.project.model.EClass;

public interface EClassService {
	List<EClass> findAll();
	Optional<EClass> findById(Long id);
	EClass[] findByPackageId(Long id);
	boolean create(EClass obj);
	boolean update(EClass obj);
	boolean delete(Long id);
}
