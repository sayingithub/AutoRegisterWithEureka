package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;


@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class AutoRegisterWithEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoRegisterWithEurekaApplication.class, args);
	}
	
	@RequestMapping("/")
	public String home(){
		serviceUrl();
		return "This is to check how the service is auto register with Eureka Server";
	}
	
	@Autowired
	private EurekaClient discoverClient;
	
	public void serviceUrl(){
		//InstanceInfo instance = DiscoveryClient.getEurekaServiceUrlsFromConfig("defaultZone", true);
		List list = (List)DiscoveryClient.getEurekaServiceUrlsFromConfig("defaultZone", true);
		for(int i = 0 ; i < list.size(); i++){
			System.out.println("LIST VALUE "+list.get(i)+"");
			InstanceInfo instance = (InstanceInfo)list.get(i);
			System.out.println("Home Page "+instance.getHomePageUrl());
			System.out.println("Health Check "+instance.getHealthCheckUrl());
			System.out.println("Host Name "+instance.getHostName());
			System.out.println("Metadata "+instance.getMetadata());
			
		}
			
		
	}
	
	
}
