package it.disim.univaq.sose.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.JsonNode;

@FeignClient("object-microservice")
public interface EObjectMicroServiceFeignClient {
	
	@RequestMapping(method=RequestMethod.GET, value="object/id/{id}")
	public JsonNode findById(@PathVariable Long id);
	
	@RequestMapping(method=RequestMethod.GET, value="object/user/{id}")
	public JsonNode[] findByUser(@PathVariable Long id);
}