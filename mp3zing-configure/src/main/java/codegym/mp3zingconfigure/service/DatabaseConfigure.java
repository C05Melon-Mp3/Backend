package codegym.mp3zingconfigure.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("codegym.mp3zingdao.entity")
public class DatabaseConfigure {
}

