package com.mapr.demo.mobileappserver.controller;

import com.mapr.demo.mobileappserver.sender.DataSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DataController {
    private final DataSenderService senderService;

    @PutMapping("/data/{deviceId}/accelerometer")
    public void putDataAccelerometer(@PathVariable(value = "deviceId") String deviceId, @RequestBody String data) {
        log.info("Accelerometer data from {}", deviceId);
        log.debug("Message: {}", data);
        senderService.sendAccelerometerData(deviceId, data);
    }

    @PutMapping("/data/{deviceId}/location")
    public void putDataLocation(@PathVariable(value = "deviceId") String deviceId, @RequestBody String data) {
        log.info("Location data from {}", deviceId);
        log.debug("Message: {}", data);
        senderService.sendLocationData(deviceId, data);
    }

    @PutMapping("/data/{deviceId}/photo")
    public void putDataPhoto(@PathVariable(value = "deviceId") String deviceId, @RequestBody String data) {
        log.info("Photo data from {}", deviceId);
        senderService.sendPhotoData(data);
    }
}
