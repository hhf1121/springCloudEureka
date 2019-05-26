package com.hhf.api.controller;

import java.net.URI;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 手写ribbon实现本地负载均衡
 * @author hhf
 *
 */

@RestController
@RequestMapping("/ribbon")
public class MyRibbonController {

	@Autowired
	private DiscoveryClient discoveryClient;//DiscoveryClient：可获取注册中心的信息

	@Autowired
	private RestTemplate restTemplate;// 由springBoot WEB组件提供

	private int Count=1;//请求总数
	
	@RequestMapping("/getServerByName")
	public String getServerByName(String name,@RequestParam(defaultValue="") String param) {
		//手写本地负载均衡->轮询方式
		List<ServiceInstance> instances = discoveryClient.getInstances("app-hhf-user");
		//服务集群总数
		int size = instances.size();
		int index=Count%size;
		Count++;
		URI uri = instances.get(index).getUri();//根据下标选择某个url
		String url = "";
		if(StringUtils.isNotBlank(param)){
			url=uri+"/user/"+name+"?yes="+param;
		}else{
			url=uri+"/user/"+name;
		}
		System.out.println(url);
		String forObject = restTemplate.getForObject(url, String.class);
		return "VIP--调用User:" + forObject.toString();
	}

}
