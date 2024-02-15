package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImpl implements UserService{

    private final RestTemplate restTemplate;

    private String resourceUrl = "http://94.198.50.185:7081/api/users/";

    private String sessionId;


    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<User[]> getAllUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(resourceUrl, User[].class);
        User[] users = response.getBody();

        for(User user : users){
            System.out.println(user.toString());
        }

        sessionId = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        return ResponseEntity.ok(users);
    }


    public String addUser(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("COOKIE", sessionId);

        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity(resourceUrl, request, String.class );

        return response.getBody();
    }

    public String updateUser (User updateUser){
        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.add("COOKIE", sessionId);

        HttpEntity<User> request = new HttpEntity<>(updateUser, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT, request, String.class);

        return response.getBody();
    }

    public String deleteUser (Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("COOKIE", sessionId);

        HttpEntity<Void> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(resourceUrl+id, HttpMethod.DELETE, request, String.class);

        return response.getBody();
    }



}
