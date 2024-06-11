package es.sneakerwebshop.repository;

import es.sneakerwebshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);

    User findUserByUserId(int userId);
}
