package service;

import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<User[]> getAllUsers();

    String addUser(User user);

    String updateUser (User updateUser);

    String deleteUser (Long id);
}
