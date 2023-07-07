package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import it.disim.univaq.sose.project.model.EAttribute;

public interface EAttributeService {
	List<EAttribute> findAll();
	Optional<EAttribute> findById(Long id);
	EAttribute[] findByClassId(Long id);
	Long create(EAttribute obj);
	boolean update(EAttribute obj);
	boolean delete(Long id);
}
