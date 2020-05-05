package kss.springframework.webservicelistener.services;

import kss.springframework.webservicelistener.model.UserDTO;
import kss.springframework.webservicelistener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public void saveUser(UserDTO user){
        userRepository.save(user);
        System.out.println("User saved, count of users: " + userRepository.count());
    }
}
