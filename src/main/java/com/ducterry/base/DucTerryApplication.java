package com.ducterry.base;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@Api(tags = "00. HealCheck", description = "Kiểm tra hoạt động Server")
public class DucTerryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DucTerryApplication.class, args);
    }

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck() {
        return new ResponseEntity<>("AWS Health Check OK !!!", HttpStatus.OK);
    }

}
