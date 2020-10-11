package com.pixeon.healthcare.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Value("${swagger.base-package}")
	private String BASEPACKAGE;

	@Value("${info.app.name}")
	private String appName;

	@Value("${info.app.version}")
	private String appVersion;

	@Value("${info.app.description}")
	private String appDescription;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(BASEPACKAGE))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title(appName).description(appDescription).version(appVersion).contact(
				new Contact("Marco Lima", "https://www.linkedin.com/in/marco-lima-9255a620", "tpd.marco@gmail.com"))
				.build();

		return apiInfo;
	}
}
