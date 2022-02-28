//package com.ducterry.base.commons.config.others;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//@Configuration
//public class OpenAPIConfig {
//    @Bean
//    public OpenAPI  customOpenAPI(){
//        return new OpenAPI()
//                .servers(
//                        Arrays.asList(
//                                new Server().url("http://localhost:8080"),
//                                new Server().url("https://ducterry.com")
//                        ))
//                .info(new Info()
//                        .title("This Base Project of Duc Terry")
//                        .description("Open API 3.0")
//                        .contact(new Contact()
//                                .email("dev.ndangduc.bn@gmail.com")
//                                .name("Duc Terry"))
//                        .license(new License()
//                                .name("Apache 2.0")
//                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
//                        .version("V1.0.0"));
//    }
//}
