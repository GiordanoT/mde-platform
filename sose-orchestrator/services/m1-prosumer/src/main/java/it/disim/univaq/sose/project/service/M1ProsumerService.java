package it.disim.univaq.sose.project.service;

import java.util.ArrayList;
import it.disim.univaq.sose.project.model.EValue;
import it.disim.univaq.sose.project.model.EObject;

public interface M1ProsumerService {
	ArrayList<EObject> findModel(Long userId);
	EObject findObject(Long id);
	EValue findValue(Long id);
	ArrayList<EValue> findValuesByObject(Long id);
}
