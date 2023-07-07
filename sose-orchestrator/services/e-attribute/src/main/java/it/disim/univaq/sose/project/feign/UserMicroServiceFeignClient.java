package it.disim.univaq.sose.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.JsonNode;

@FeignClient("user-microservice")
public interface UserMicroServiceFeignClient {

	@RequestMapping(method=RequestMethod.GET, value="user/id/{id}")
	public JsonNode findById(@PathVariable Long id);
}