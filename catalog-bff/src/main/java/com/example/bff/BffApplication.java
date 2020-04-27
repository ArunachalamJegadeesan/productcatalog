package com.example.bff;

import com.example.bff.interceptor.LoggingClientInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
@EnableSwagger2
public class BffApplication {

	@Autowired
	private LoggingClientInterceptor loggingClientInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(BffApplication.class, args);
	}


	@Bean
	public RestTemplate template() {
		RestTemplate template = new RestTemplate();
		template.setInterceptors(Collections.unmodifiableList(Arrays.asList(new ClientHttpRequestInterceptor[] {loggingClientInterceptor})));
		return template;
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.bff"))
				.paths(regex("/bff.*"))
				.build();
	}

}
