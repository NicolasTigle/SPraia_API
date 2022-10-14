package br.com.zeniti.spraia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SPraiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SPraiaApplication.class, args);
	}

}
