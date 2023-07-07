package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import it.disim.univaq.sose.project.model.EPackage;

public interface EPackageService {
	List<EPackage> findAll();
	Optional<EPackage> findById(Long id);
	EPackage[] findByUserId(Long id);
	boolean create(EPackage obj);
	boolean update(EPackage obj);
	boolean delete(Long id);
}
