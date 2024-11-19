package org.example;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    HttpHeaders headers = new HttpHeaders();
    private String SESSION_ID;

    public void getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        SESSION_ID = responseEntity.getHeaders().getFirst("Set-Cookie");
    }

    public void saveUser(User user) {
        headers.set("Cookie", SESSION_ID);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void updateUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void deleteUser(Long id) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println(responseEntity.getBody());
    }
}
