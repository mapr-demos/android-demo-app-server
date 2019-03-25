package com.mapr.demo.mobileappserver;

import com.mapr.demo.mobileappserver.controller.DataController;
import com.mapr.demo.mobileappserver.kafka.AdminService;
import com.mapr.demo.mobileappserver.kafka.KafkaClient;
import com.mapr.demo.mobileappserver.sender.DataSenderConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mapr.demo.mobileappserver.kafka.util.KafkaNameUtility.convertToKafkaTopic;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MobileAppServerApplicationTests {
    public List<String> incomingData = Collections.synchronizedList(new ArrayList<>());

    @Autowired
    public DataController controller;

    @Autowired
    public KafkaClient client;

    @Autowired
    public AdminService admin;

    @Autowired
    public DataSenderConfig config;

    private String streamPath;

    @Before
    public void init() {
        streamPath = config.getPathToStream();
    }

    @After
    public void clean() {
        if (admin.streamExists(streamPath))
            admin.removeStream(streamPath);
    }

    @Test
    public void contextLoadTest() {
    }

    @Test
    public void sendTest() throws InterruptedException {
        String deviceId = "1";
        String data = "{}";
        controller.putDataAccelerometer(deviceId, data);
        client.subscribe(Collections.singleton(convertToKafkaTopic(streamPath, "accelerometer_1")))
                .subscribe(this::handleMessage);
        Thread.sleep(5000);

        Assert.assertEquals(1, incomingData.size());
        Assert.assertEquals(data, incomingData.get(0));
    }

    private void handleMessage(ConsumerRecord<String, byte[]> record) {
        incomingData.add(new String(record.value()));
    }

}
