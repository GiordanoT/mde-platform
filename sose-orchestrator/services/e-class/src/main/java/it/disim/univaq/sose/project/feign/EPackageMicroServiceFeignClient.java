package it.disim.univaq.sose.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.JsonNode;

@FeignClient("package-microservice")
public interface EPackageMicroServiceFeignClient {

	@RequestMapping(method=RequestMethod.GET, value="package/id/{id}")
	public JsonNode findById(@PathVariable Long id);
}