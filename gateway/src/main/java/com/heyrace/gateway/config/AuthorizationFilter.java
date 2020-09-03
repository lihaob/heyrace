package com.heyrace.gateway.config;

import com.ctc.wstx.util.StringUtil;
import com.heyrace.gateway.feign.IFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {
    @Autowired
    private IFeign feign;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("abc");
        ServerHttpRequest request =  exchange.getRequest();
        //判断路径
        String token = "";
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if (cookies != null) {
            for (String cookie_name: cookies.keySet()) {
                if (cookie_name.equals("accessToken")) {
                    List<HttpCookie> httpCookies = cookies.get(cookie_name);
                    token = httpCookies.get(0).getValue();
                    break;
                }
            }
        }
        String url = request.getURI().toString();
        if (url.contains("/course/login")||(!StringUtils.isEmpty(token)&&feign.hasKey(token))) {
            return chain.filter(exchange);//放行
        }
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();//拦截
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
