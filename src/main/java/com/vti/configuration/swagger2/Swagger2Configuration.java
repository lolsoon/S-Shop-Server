package com.vti.configuration.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "TEST Application",
                "This is a simple server for a electronics shop",
                "1.0.0",
                "https://github.com/lolsoon/SShop.git",
                new Contact("SonDinhKhac", "https://www.facebook.com/sondinhkhac1998/", "sondinhkhac1998@gmail.com"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html",
                Collections.emptyList()
        );
    }
}
