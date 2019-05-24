package com.hhf.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

@RestController
@RequestMapping("/VIP")
public class VIPApiController {

	@Autowired
	private DiscoveryClient discoveryClient;//DiscoveryClient：可获取注册中心的信息

	@Autowired
	private RestTemplate restTemplate;// 由springBoot WEB组件提供

	
	
	// 在springCloud中，两种方式调用rest和fegin（springcloud）
	@RequestMapping("getUserStr")
	public String getUserStr() {
		// VIP--调用User,1.通过httpClient写死。2.通过注册Eureka上的别名(集群负载均衡，使用别名)
		String resukt = restTemplate.getForObject("http://app-hhf-user/user/getUserStr", String.class);// 1
		return "VIP--调用User:" + resukt;
	}

	@RequestMapping("getUserData")
	public String getVIP(Integer type) {
		// VIP--调用User,1.通过httpClient写死。2.通过注册Eureka上的别名
//		HttpHeaders headers = new HttpHeaders();
//		MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
//		headers.setContentType(mediaType);
//		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//		Map<String, Integer> hashMap = new HashMap<String, Integer>();
//		hashMap.put("yes", type);
//		HttpEntity<Map<String, Integer>> httpEntity = new HttpEntity<Map<String, Integer>>(hashMap, headers);
		  Map result = restTemplate.getForObject("http://app-hhf-user/user/getUserData?yes="+type, Map.class);// 2
//		 Map result = postForEntity.getBody();
		 // 如果想以别名方式调用服务，restTemplate对象需要依赖ribbon负载均衡器
		// 注解@LoadBalanced能让这个restTemplate实例在请求时拥有客户端负载均衡的能力。
		return "VIP--调用User:" + result;
	}
	
	@RequestMapping("getEurekaData")
	public Map<String,Object> getEurekaData(String name){
		Map<String,Object> map=Maps.newHashMap();
		List<ServiceInstance> instances = discoveryClient.getInstances(name);
		map.put("data", instances);
		map.put("success", true);
		return map;
	}

}
