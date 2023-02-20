package wipro.latam.consultacep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
public class ConsultaCepApplication {

    public static void main(String[] args) {

        //Método main iniciado pelo Spring Boot
        SpringApplication.run(ConsultaCepApplication.class, args);
    }
}
