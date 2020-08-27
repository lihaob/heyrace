package com.heyrace.course_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.heyrace")
@MapperScan("com.heyrace.mappers")
@EnableCaching
public class CourseSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseSystemApplication.class, args);
    }

}
