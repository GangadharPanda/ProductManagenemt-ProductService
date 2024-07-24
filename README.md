**Product Service - Microservice**
* When we have multiple instances of Auth service running at multiple servers
* It will not be a wise decision to hard code the URL of a server where AuthService is Running
* To fix that out, we have a Service Discovery --> Eureka Server which Just keeps a track of
* All the running Instances of all the services , this is a kind of dashboard (a separate microservice
* known as Service Discovery)
* ProductService and AuthService are two client of that Service Discovery
* How Does Service Discovery help to use any service instance running on any server
* (Server1, Server2, or Server3)
* While Using the RestTemplate we will use it
* Earlier restTemplate.getForObject("http://localhost:8090/auth/validate/test", Boolean.class);
* Now restTemplate.getForObject("http://AUTHSERVICE/auth/validate/test", Boolean.class);
* This Discovery services Just returns an array of the Ip Address and port where all the Auth Service is running
* Example
* AUTHSERVICE : [DESKTOP-BP9LL64:AuthService:8090 , DESKTOP-BP9LL64:AuthService:8091 , DESKTOP-BP9LL64:AuthService:8092]
* Who does the Load balancing then?
* It's responsibility of the RestTemplate to do that load Balancing
* for that we need 2 change in our code
* 1. Add @LoadBalanced in RestTemplate bean definition
* 2. Import that bean from import org.springframework.cloud.client.loadbalancer.LoadBalanced;
 
application.properties
 
  ```
spring.application.name=ProductService
# This Port number is defined in the Environment Variable
server.port=${SERVER_PORT}
eureka.client.registerWithEureka=true
eureka.client.fetchRegistry=true
eureka.client.serviceUrl.defaultZone  = http://localhost:8761/eureka
```
pom.xml
```
<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-eureka-client -->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    <version>4.1.1</version>
  </dependency>
```
ApplicationConfig.java
```
package com.ps.service.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Configuration
public class ApplicationConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```
FakeProductService.java
```
@Override
public void deleteProduct(Long id) {
    //boolean isValid = restTemplate.getForObject("http://localhost:8090/auth/validate/test", Boolean.class);
    boolean isValid = restTemplate.getForObject("http://AUTHSERVICE/auth/validate/test", Boolean.class);
    if (isValid) {
        System.out.println("Deleting product after Validating it from Auth Service");
    }
    //restTemplate.delete("https://fakestoreapi.com/products/" + id);
}
```

**Note** : Now this service is dependent on two more services , AuthService and Service Discovery (Eureka Server).We will need both those servers running before starting this one. 

