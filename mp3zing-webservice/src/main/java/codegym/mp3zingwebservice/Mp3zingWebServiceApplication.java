package codegym.mp3zingwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing()
@EnableJpaRepositories(basePackages= "codegym.mp3zingdao.repository")
@ComponentScan("codegym")
@EntityScan("codegym.mp3zingdao.entity")
public class Mp3zingWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mp3zingWebServiceApplication.class, args);
    }

}