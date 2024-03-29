package br.com.api.assembleia.configuracoes;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI assembleiaApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Assembleia")
                        .description("API REST de cadastro de pautas e votações.")
                        .contact(new Contact()
                                .name("Carlos Jessiel")
                                .url("https://www.linkedin.com/in/carlos-jessiel-nunez-soares/")
                                .email("c.jessiel_nunez@hotmail.com"))
                        .license(new License()
                                .name("Apache License Version 2.0")
                                .url("https://www.apache.org/licenses/")));

    }
}


