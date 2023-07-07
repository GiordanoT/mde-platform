package it.disim.univaq.sose.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.JsonNode;

@FeignClient("metamodel-microservice")
public interface MetamodelMicroServiceFeignClient {

	@RequestMapping(method=RequestMethod.GET, value="metamodel/user/{id}")
	public JsonNode findByUserId(@PathVariable Long id);
}