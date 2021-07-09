package ru.rest.template.demo;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.rest.template.demo.model.User;
import java.util.List;


public class Aplication {

    private static String urlServer = "http://91.241.64.178:7081/api/users";
    private static RestTemplate restTemplate;
    private static HttpHeaders headers;


    public static void main(String[] args) {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<User[]> responseEntityGET = restTemplate.getForEntity(urlServer, User[].class);
        List<String> cookie = responseEntityGET.getHeaders().get("Set-Cookie");
        headers.add("Cookie", String.join(";", cookie));


        User user = new User(3L, "James", "Brown", (byte) 1);
        String code;

        code = getCode(urlServer, user, HttpMethod.POST);
        user.setName("Thomas");
        user.setLastName("Shelby");
        code += getCode(urlServer, user, HttpMethod.PUT);
        code += getCode(urlServer + "/3", user, HttpMethod.DELETE);

        System.out.println(code);

    }

    public static String getCode(String url, User user, HttpMethod method) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, new HttpEntity<User>(user, headers), String.class);
        return responseEntity.getBody();
    }
}
