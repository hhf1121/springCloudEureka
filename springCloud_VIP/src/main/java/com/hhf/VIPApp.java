package com.hhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class VIPApp {

	public static void main(String[] args) {
		SpringApplication.run(VIPApp.class, args);
	}
	
	
	
	//需要往springBoot容器中注册restTemplate的bean。
	@Bean//注入bean
//	@LoadBalanced//能让这个restTemplate实例在请求时拥有客户端负载均衡的能力。          手写负载均衡，注释掉@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
