package com.reservation.ms_reservation.client;

import com.reservation.ms_reservation.dto.RessourceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-ressources")
public interface RessourceClient {

    @GetMapping("/ressources/{id}")
    RessourceDTO getRessourceById(@PathVariable Long id);
}