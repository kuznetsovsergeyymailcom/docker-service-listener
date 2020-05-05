package kss.springframework.webservicelistener.repository;


import kss.springframework.webservicelistener.model.UserDTO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDTO, Long> {
}
