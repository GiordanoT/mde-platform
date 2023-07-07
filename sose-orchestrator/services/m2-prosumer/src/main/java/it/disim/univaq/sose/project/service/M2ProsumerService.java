package it.disim.univaq.sose.project.service;

import java.util.ArrayList;

import it.disim.univaq.sose.project.model.EAttribute;
import it.disim.univaq.sose.project.model.EClass;
import it.disim.univaq.sose.project.model.EPackage;

public interface M2ProsumerService {
	ArrayList<EPackage> findMetaModel(Long userId);
	EPackage findPackage(Long id);
	EClass findClass(Long id);
	EAttribute findAttribute(Long id);
	ArrayList<EClass> findClassesByPackage(Long id);
	ArrayList<EAttribute> findAttributesByClass(Long id);
}
