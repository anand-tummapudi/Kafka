package com.anand.kafka.booking.controller;

import com.anand.kafka.booking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PutMapping
    public ResponseEntity<Map<String,String>> updateLocation(){

        int range = 100;
        while(range>0){
            //System.out.println();
            locationService.updateLocation(Math.random()+"."+Math.random());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            range--;
        }

        return new ResponseEntity<>(Map.of("message","Location updated"), HttpStatus.OK);
    }
}
