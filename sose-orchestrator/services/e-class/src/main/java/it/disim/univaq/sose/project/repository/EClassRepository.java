package it.disim.univaq.sose.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.disim.univaq.sose.project.model.EClass;

public interface EClassRepository extends JpaRepository<EClass, Long> {
	EClass[] findByPackageId(Long id);
	EClass[] findByUserId(Long id);
}
