package it.disim.univaq.sose.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import it.disim.univaq.sose.project.model.EAttribute;

public interface EAttributeRepository extends JpaRepository<EAttribute, Long> {
	EAttribute[] findByClassId(Long id);
	Optional<EAttribute> findByClassIdAndName(Long id, String name);
	EAttribute[] findByUserId(Long id);
}
