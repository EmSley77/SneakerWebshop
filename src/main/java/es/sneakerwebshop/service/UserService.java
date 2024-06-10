package es.sneakerwebshop.service;
/*Emanuel sleyman
2024-06-10
this class is a service that is responsible for User Entity methods
*/
import es.sneakerwebshop.repository.UserRepository;


public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
