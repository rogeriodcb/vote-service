package com.voteservice.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* <p>**************************************************************************************
* <p>                                 SwaggerConfig
* <p>**************************************************************************************
* <p>
* Configures Swagger - an Interface Description Language for describing RESTful APIs
* expressed using JSON. 
*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket voteServiceAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.voteservice"))
				.paths(regex("/api/v1.*"))
				.build()
				.apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		return new ApiInfoBuilder().title("Vote Service API").description("Rest API for cooperative vote service.")
				.version("1.0.0").termsOfServiceUrl("Terms of service")
				.contact(new Contact("Rogério de Carvalho Brito", "https://sites.google.com/view/rogeriocb",
						"krcbrito@gmail.com"))
				.license("Apache License Version 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").build();
	
	}
}
