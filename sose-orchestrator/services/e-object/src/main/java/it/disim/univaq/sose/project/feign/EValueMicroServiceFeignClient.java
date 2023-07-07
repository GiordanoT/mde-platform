package it.disim.univaq.sose.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.JsonNode;

@FeignClient("value-microservice")
public interface EValueMicroServiceFeignClient {
	
	@RequestMapping(method=RequestMethod.POST, value="value")
	public void create(@RequestBody JsonNode obj);
	
	@RequestMapping(method=RequestMethod.GET, value="value/object/{id}")
	public JsonNode[] findByObjectId(@PathVariable Long id);
	
}