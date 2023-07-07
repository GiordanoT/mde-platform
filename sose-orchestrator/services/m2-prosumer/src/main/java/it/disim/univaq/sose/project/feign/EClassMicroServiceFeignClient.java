package it.disim.univaq.sose.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.JsonNode;

@FeignClient("class-microservice")
public interface EClassMicroServiceFeignClient {
	
	@RequestMapping(method=RequestMethod.GET, value="class/id/{id}")
	public JsonNode findById(@PathVariable Long id);

	@RequestMapping(method=RequestMethod.GET, value="class/package/{id}")
	public JsonNode[] findByPackageId(@PathVariable Long id);
}