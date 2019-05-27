package com.hhf.fegin;

import java.util.Map;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 1.引入pom依赖spring-cloud-starter-openfeign
 * 2.@EnableFeignClients 在消费方里开启fegin
 * 3.fegin接口的编写，@FeignClient注册提供方的serverName
 * 4.@RequestMapping 注册提供方的接口名称
 * 5.在controller层注入fegin接口对象
 * 6.消费方使用fegin接口对象调用提供方接口
 * 
 * 
 * 通过fegin方式远程调用
 * 需要传参用@RequestParam，需要加name属性,否则启动报错
 * @author hhf
 *
 */

//指定名称，将user注册进来。
@FeignClient(name="app-hhf-user")
public interface UserApiFegin {

	@RequestMapping(value="/user/getUserData")
	public Map<String,Object> getUserData(@RequestParam(name="yes") Integer yes);
	
}
