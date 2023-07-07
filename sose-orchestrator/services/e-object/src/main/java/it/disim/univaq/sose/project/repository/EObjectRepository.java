package it.disim.univaq.sose.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.disim.univaq.sose.project.model.EObject;

public interface EObjectRepository extends JpaRepository<EObject, Long> {
	EObject[] findByInstanceOf(Long id);
	EObject[] findByUserId(Long id);
}
