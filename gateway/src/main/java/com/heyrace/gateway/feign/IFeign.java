package com.heyrace.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "course",path = "/course")
public interface IFeign {
    @GetMapping("/redis/hasKey/{key}")
    Boolean hasKey(@PathVariable String key);
}
