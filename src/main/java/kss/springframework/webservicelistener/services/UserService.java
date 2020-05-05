package kss.springframework.webservicelistener.services;


import kss.springframework.webservicelistener.model.UserDTO;

public interface UserService {
    void saveUser(UserDTO user);
}
