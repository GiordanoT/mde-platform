package it.disim.univaq.sose.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.JsonNode;

@FeignClient("attribute-microservice")
public interface EAttributeMicroServiceFeignClient {
	
	@RequestMapping(method=RequestMethod.GET, value="attribute/class/{id}")
	public JsonNode[] findByClassId(@PathVariable Long id);

	@RequestMapping(method=RequestMethod.GET, value="attribute/id/{id}")
	public JsonNode findById(@PathVariable Long id);
}