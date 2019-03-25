package com.mapr.demo.mobileappserver.sender;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.mapr.demo.mobileappserver.kafka.util.KafkaNameUtility.convertToKafkaFormat;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class DataSenderConfig {

    private String stream = "mobile";
    private String folder = "";

    public String getPathToStream() {
        return convertToKafkaFormat(getFolder(), getStream());
    }
}
