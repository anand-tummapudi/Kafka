package com.anand.kafka.booking.config;

import com.anand.kafka.booking.util.AppConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class LocationConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder.
                name(AppConstants.CAB_LOCATION)
                .build();
    }

}

