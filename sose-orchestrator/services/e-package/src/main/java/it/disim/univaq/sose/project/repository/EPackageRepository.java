package it.disim.univaq.sose.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.disim.univaq.sose.project.model.EPackage;

public interface EPackageRepository extends JpaRepository<EPackage, Long> {
	EPackage[] findByUserId(Long id);
}
