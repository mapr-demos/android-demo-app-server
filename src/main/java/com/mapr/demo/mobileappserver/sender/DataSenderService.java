package com.mapr.demo.mobileappserver.sender;

import com.mapr.demo.mobileappserver.kafka.AdminService;
import com.mapr.demo.mobileappserver.kafka.KafkaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.mapr.demo.mobileappserver.kafka.util.KafkaNameUtility.convertToKafkaTopic;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DataSenderService {
    private final static String PHOTO_TOPIC = "photo";
    private final static String ACCELEROMETER_TOPIC_PATTERN = "accelerometer_%s";
    private final static String LOCATION_TOPIC_PATTERN = "location_%s";

    private final KafkaClient client;
    private final AdminService admin;
    private final DataSenderConfig config;

    private String streamPath;

    @PostConstruct
    public void init() {
        streamPath = config.getPathToStream();
        admin.createStreamIfNotExists(streamPath);
    }

    public void sendAccelerometerData(String androidId, String data) {
        send(format(ACCELEROMETER_TOPIC_PATTERN, androidId), data);
    }

    public void sendLocationData(String androidId, String data) {
        send(format(LOCATION_TOPIC_PATTERN, androidId), data);
    }

    public void sendPhotoData(String data) {
        send(PHOTO_TOPIC, data);
    }

    private void send(String topic, String data) {
        admin.createStreamAndTopicIfNotExists(streamPath, topic);
        client.publish(convertToKafkaTopic(streamPath, topic), data.getBytes()).subscribe();
    }
}
