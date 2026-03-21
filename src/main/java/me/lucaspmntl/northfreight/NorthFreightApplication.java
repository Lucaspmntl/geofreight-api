package me.lucaspmntl.northfreight;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(
		title = "NorthFreight API",
		version = "1.0.0",
		description = "API para cálculo de fretes regionais (Amapá)",
		contact = @Contact(url = "https://github.com/Lucaspmntl", name = "Lucas Pimentel")
))
public class NorthFreightApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorthFreightApplication.class, args);
	}
}
