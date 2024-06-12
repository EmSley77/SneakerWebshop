package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-12
 * this class is a service that is responsible for Admin users
 */

import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class AdminService {

    private UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createAdminAccount(String name, String lastname, String email, String password, int telephoneNumber, String address) {
        try {

            User ifUserExists = userRepository.findByEmailAndPassword(email, password);

            if (ifUserExists != null) {
                return "User with given credentials already exists, try other credentials";
            }

            User user = new User();
            user.setName(name);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setPassword(password);
            user.setTelephoneNumber(telephoneNumber);
            user.setAddress(address);
            user.setRole(1);
            user.setRegistrationDate(Date.valueOf(LocalDate.now()));

            userRepository.save(user);
            return "Admin added";

        } catch (Exception e) {
            e.printStackTrace();
            return "could not create account";
        }
    }



}
