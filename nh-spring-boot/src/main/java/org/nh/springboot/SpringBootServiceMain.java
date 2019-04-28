package org.nh.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "org.nh" })
public class SpringBootServiceMain {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootServiceMain.class);

    public static void main(String[] args) {
        SpringApplicationBuilder application = new SpringApplicationBuilder(SpringBootServiceMain.class).web(true);
        application.run(args);
        /*SpringApplication application = new SpringApplication(SpringBootServiceMain.class);
        application.run(args);*/
        logger.info("SpringBoot启动成功.");
    }

}
