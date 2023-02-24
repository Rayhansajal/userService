package com.user.service.userService.external.services;

import com.user.service.userService.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServices {

    @GetMapping("/hotel/{hotelId}")
    Hotel gethotel(@PathVariable("hotelId") String HotelId);
}
