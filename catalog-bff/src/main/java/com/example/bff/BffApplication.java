package com.example.bff;

import com.example.bff.interceptor.LoggingClientInterceptor;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
@EnableDiscoveryClient
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

}
