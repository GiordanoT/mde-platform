package it.disim.univaq.sose.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.JsonNode;

@FeignClient("model-microservice")
public interface ModelMicroServiceFeignClient {

	@RequestMapping(method=RequestMethod.GET, value="model/user/{id}")
	public JsonNode findByUserId(@PathVariable Long id);
}