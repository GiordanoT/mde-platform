package it.disim.univaq.sose.project.service;

import java.util.List;
import java.util.Optional;
import it.disim.univaq.sose.project.model.EValue;

public interface EValueService {
	List<EValue> findAll();
	Optional<EValue> findById(Long id);
	EValue[] findByObjectId(Long id);
	boolean create(EValue obj);
	boolean update(EValue obj);
	boolean delete(Long id);
}
