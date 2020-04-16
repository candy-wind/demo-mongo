package com.example.demomongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:bean.xml")
public class DemoMongoApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext=SpringApplication.run(DemoMongoApplication.class, args);
        applicationContext.getBean("bean");
    }

}
