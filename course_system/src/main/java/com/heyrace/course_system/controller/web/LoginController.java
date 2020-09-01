package com.heyrace.course_system.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/login")
    public void authorization_code(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("client_id","fe96082c57de1d7f6612");
        map.put("client_secret","aa281699d3acd4eef156cea87d9f762b3ec4f9ab");
        map.put("code",code);
        map.put("state","heyrace");
        map.put("redirect_uri","http://localhost:9001/course/login");
        Map<String, String> resp = restTemplate.postForObject("https://github.com/login/oauth/access_token",map,Map.class);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","token "+resp.get("access_token"));
        headers.add("accept","application/vnd.github.v3+json");
        HttpEntity<Map> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> entity = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, Map.class);
        System.out.println(entity.getBody());
    }
}
