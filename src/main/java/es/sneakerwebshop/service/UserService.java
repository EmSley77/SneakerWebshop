package es.sneakerwebshop.service;
/*
 *Emanuel sleyman
 *2024-06-10
 *this class is a service that is responsible for User Entity methods
 * use session to save userId to be able to get it at anytime and make user experience smooth
 * created new account, login, delete account and edit account int this service, aswell as rest api methods
 */

import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UserService {

    private UserRepository userRepository;

    private HttpSession session;

    @Getter
    private int userId;

    public UserService(UserRepository userRepository, HttpSession session) {
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

    public String login(String email, String password) {

        try {

            User findExistingUser = userRepository.findByEmailAndPassword(email, password);
            //can use Optional as second option for this method part
            if (findExistingUser == null) {
                return "could not find user with given credentials, try again";
            }

            if (email.equals(findExistingUser.getEmail()) && password.equals(findExistingUser.getPassword())) {
                session.setAttribute("userId", findExistingUser.getUserId());
                userId = (int) session.getAttribute("userId");
                return "login granted";
            }

            return "could not sign in";
        } catch (Exception e) {
            e.printStackTrace();
            return "could not sign in";
        }
    }


    public String deleteAccount(String email, String repeatEmail, String password, String repeatPassword) {

        //can use Optional instead but either works
        User user = userRepository.findByEmailAndPassword(email, password);

        if (user == null) {
            return "wrong login credentials";
        }

        if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
            if (repeatEmail.equals(email) && repeatPassword.equals(password)) {
                userRepository.delete(user);
                return "Account deletion was successful";
            }
        }

        return "could not delete user";
    }


    //edit user information, make these parameters required false in controller so no null exception is made
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

    //get user information, view in account page for then user to edit account
    public User getAccount() {
        return userRepository.findUserByUserId(userId);
    }


}
