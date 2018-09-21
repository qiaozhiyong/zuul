package com.example.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@RestController
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public MyFilter accessFilter() {
        return new MyFilter();
    }
    //这些接口不走过滤器MyFilter
    @RequestMapping("/ping")
    public String ping() {
        return "ok";
    }
    //这些接口不走过滤器MyFilter
    @RequestMapping("/check")
    public String check(@RequestParam String aa) {
        return "ok" + aa;
    }
}
