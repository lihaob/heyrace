package com.heyrace.course_system.controller.web;

import com.heyrace.beans.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
public class LoginController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/login")
    public RespBean authorization_code(String code, HttpServletResponse response) {
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
        ResponseEntity<Map> entity = null;
        try {
            entity = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, Map.class);
        } catch (RestClientException e) {
            return RespBean.error("error");
        }
        System.out.println(entity.getBody());
        if (entity.getStatusCode() == HttpStatus.OK ) {
            String flag = entity.getBody().get((Object)"id").toString() + ":" + System.currentTimeMillis();
            stringRedisTemplate.opsForValue().set(flag, "1", (long) (3 * 60), TimeUnit.SECONDS);
            Cookie cookie = new Cookie("accessToken", flag);
            cookie.setMaxAge(60*3);
            cookie.setPath("/");
            response.addCookie(cookie);
            return RespBean.success("success");
        } else {
            return RespBean.error("error");
        }
    }

    @RequestMapping("/redis/hasKey/{key}")
    public Boolean hasKey(@PathVariable("key") String key) {
        try {
            return stringRedisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/logout/{token}")
    RespBean logout(HttpServletRequest request,@PathVariable String token) {
        try {
            stringRedisTemplate.delete(token);
        } catch (Exception e) {
            return RespBean.error("logout fail");
        }
        return RespBean.success("logout success");
    }
}
