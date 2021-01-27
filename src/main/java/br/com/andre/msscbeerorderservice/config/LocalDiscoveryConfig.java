package br.com.andre.msscbeerorderservice.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@EnableEurekaClient
@Configuration
public class LocalDiscoveryConfig {
}
