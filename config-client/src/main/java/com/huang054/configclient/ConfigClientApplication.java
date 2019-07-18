package com.huang054.configclient;

import com.huang054.configclient.config.MyHealth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
public class ConfigClientApplication {

	private final ContextRefresher contextRefresher;

	private final Environment environment;
	public ConfigClientApplication(ContextRefresher contextRefresher, Environment environment) {
		this.contextRefresher = contextRefresher;
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@Scheduled(fixedRate=5000,initialDelay = 3000)
	public void refresher(){
		Set<String> strings=contextRefresher.refresh();
		strings.forEach(name->System.out.println(environment.getProperty(name)));

	}
	@Bean
	public MyHealth myHealth(){
		return new MyHealth();
	}

}
