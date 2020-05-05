package kss.springframework.webservicelistener.listener;

import kss.springframework.model.User;
import kss.springframework.webservicelistener.model.UserDTO;
import kss.springframework.webservicelistener.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqListener {

    private final UserService userService;

    public void receiveMessage(User user) {

        UserDTO userDTO = UserDTO.builder().name(user.getName()).build();

        log.info("Message processed...");

        userService.saveUser(userDTO);

    }
}