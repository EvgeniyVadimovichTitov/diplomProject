package ru.gb.EuricaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EuricaServerApp {
	//Eurica-сервер из коробки
	public static void main(String[] args) {
		SpringApplication.run(EuricaServerApp.class, args);
	}

}
