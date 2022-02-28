package com.ducterry.base;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties
@EnableScheduling
@Api(tags = "01. HealCheck", produces = "Kiểm tra hoạt động Server")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }, scanBasePackages = "com.ducterry")
public class DucTerryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DucTerryApplication.class, args);
	}

	@GetMapping("/health-check")
	public ResponseEntity<?> healthCheck() {
		return new ResponseEntity<>("AWS Health Check OK !!!", HttpStatus.OK);
	}

}
