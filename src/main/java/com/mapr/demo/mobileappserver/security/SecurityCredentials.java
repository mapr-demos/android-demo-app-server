package com.mapr.demo.mobileappserver.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityCredentials {
    private String username = "mapr";
    private String password = "mapr";
}
