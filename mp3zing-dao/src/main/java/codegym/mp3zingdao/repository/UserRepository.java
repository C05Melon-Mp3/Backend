package codegym.mp3zingdao.repository;

import codegym.mp3zingdao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

    List<User> findAll();
}
