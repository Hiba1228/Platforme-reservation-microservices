package com.reservation.ms_reservation.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mes-config-ms")
@Data
public class ReservationConfig {
    private Integer reservationsLast = 10;
}