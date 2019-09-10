package codegym.mp3zingwebservice;

import codegym.mp3zingconfigure.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//@Configuration
@SpringBootApplication
@EnableJpaAuditing()
@EnableJpaRepositories(basePackages= "codegym.mp3zingdao.repository")
@ComponentScan("codegym")
//
//@ComponentScan(basePackages = {"codegym.mp3zingservice.service", "codegym.mp3zingservice.service.impl", "codegym.mp3zingdao.entity", "codegym.mp3zingwebservice.controller", "codegym.mp3zingconfigure.service"}, basePackageClasses = WebSecurityConfig.class)
public class Mp3zingWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mp3zingWebServiceApplication.class, args);
    }

}
