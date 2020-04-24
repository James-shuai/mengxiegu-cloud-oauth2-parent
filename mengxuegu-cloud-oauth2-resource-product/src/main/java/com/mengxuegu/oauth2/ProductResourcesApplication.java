package com.mengxuegu.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ys
 * @date 2020/4/23 9:45
 */
@EnableEurekaClient
@SpringBootApplication
public class ProductResourcesApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductResourcesApplication.class,args);
  }

}
