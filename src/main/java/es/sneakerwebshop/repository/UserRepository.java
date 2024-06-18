package es.sneakerwebshop.repository;

import es.sneakerwebshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);

    User findUserByUserId(int userId);

    User findByEmail(String newEmail);

    List<User> findUsersByRole(int i);
}
