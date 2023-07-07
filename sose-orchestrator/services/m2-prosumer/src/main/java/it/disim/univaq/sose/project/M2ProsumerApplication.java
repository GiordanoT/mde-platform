package it.disim.univaq.sose.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class M2ProsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(M2ProsumerApplication.class, args);
	}

}
