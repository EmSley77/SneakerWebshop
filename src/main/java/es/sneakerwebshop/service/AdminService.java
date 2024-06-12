package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-12
 * this class is a service that is responsible for Admin users
 */

import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class AdminService {

    private UserRepository userRepository;

    private HttpSession session;

    @Getter
    private int userId;

    public AdminService(UserRepository userRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.session = session;
    }

    public String createAccount(String name, String lastname, String email, String password, int telephoneNumber, String address) {
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
            user.setRole(0);
            user.setRegistrationDate(Date.valueOf(LocalDate.now()));

            userRepository.save(user);
            return "Your account has been successfully created, enjoy shopping " + name;

        } catch (Exception e) {
            e.printStackTrace();
            return "could not create account";
        }
    }
    //edit Admin information, make these parameters required false in controller so no null exception is made
    public String editAccount(String newName, String newLastname, String newEmail, String newPassword, Integer newTelephoneNumber, String newAddress) {
        try {
            User user = userRepository.findUserByUserId(userId);
            if (user == null) {
                return "Could not find person to edit";
            }

            if (newName != null && !newName.isEmpty()) {
                user.setName(newName);
            }

            if (newLastname != null && !newLastname.isEmpty()) {
                user.setLastname(newLastname);
            }

            if (newEmail != null && !newEmail.isEmpty()) {
                user.setEmail(newEmail);
            }
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }

            if (newTelephoneNumber != null) {
                user.setTelephoneNumber(newTelephoneNumber);
            }

            if (newAddress != null && !newAddress.isEmpty()) {
                user.setAddress(newAddress);
            }

            userRepository.save(user);
            return "successfully updated account information";

        } catch (Exception e) {
            e.printStackTrace();
            return "Could not update account information";
        }
    }


}
