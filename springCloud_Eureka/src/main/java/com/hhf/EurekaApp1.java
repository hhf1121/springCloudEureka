package com.hhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * springCloud注册中心支持：Eureka/consul/zookeeper
 * 启动EurekaServer
 * @author hhf
 *
 */


@SpringBootApplication
@EnableEurekaServer//作为Eureka服务端
public class EurekaApp1 {
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaApp1.class, args);
	}

}
