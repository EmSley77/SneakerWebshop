
package es.sneakerwebshop.service;
/*Emanuel sleyman
2024-06-10
this class is a service that is responsible for User Entity methods
*/
import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.repository.UserRepository;
import org.hibernate.dialect.SpannerSqlAstTranslator;

import java.sql.Date;
import java.time.LocalDate;


public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String createAccount(String name, String lastname, String email,String password, int telephoneNumber, String address){
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


}
