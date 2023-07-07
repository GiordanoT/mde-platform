package it.disim.univaq.sose.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.disim.univaq.sose.project.model.EValue;

public interface EValueRepository extends JpaRepository<EValue, Long> {
	EValue[] findByObjectId(Long id);
	EValue[] findByUserId(Long id);
}
