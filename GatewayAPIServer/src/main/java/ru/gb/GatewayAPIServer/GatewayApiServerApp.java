package ru.gb.GatewayAPIServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApiServerApp {
//API Geteway из коробки маршруты в application.yaml
	public static void main(String[] args) {
		SpringApplication.run(GatewayApiServerApp.class, args);
	}

}
