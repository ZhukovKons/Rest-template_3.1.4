package ru.rest.template.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.rest.template.demo.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


public class Aplication {

    private static String urlServer = "http://91.241.64.178:7081/api/users";
    ;

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers;

        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(urlServer, User[].class);
        headers = responseEntity.getHeaders();
        List<String> cookie = headers.get("Set-Cookie");



    }
}
