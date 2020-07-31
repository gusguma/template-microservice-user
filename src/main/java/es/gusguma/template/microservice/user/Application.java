package es.gusguma.template.microservice.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})// Not: /error
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // mvn clean spring-boot:run || java -jar *.jar
    }

}
