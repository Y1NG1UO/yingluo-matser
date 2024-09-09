package com.yingluo.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class YingluoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingluoAdminApplication.class, args);
    }

}
