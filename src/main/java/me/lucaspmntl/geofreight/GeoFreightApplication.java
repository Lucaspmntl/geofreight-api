package me.lucaspmntl.geofreight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GeoFreightApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoFreightApplication.class, args);
	}

}
