package es.sneakerwebshop.ui;

import es.sneakerwebshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("sneaker-reg-page")
    public String getRegistrationPage() {
        return "sneaker_registrationpage";
    }

    @PostMapping("sneaker-register-user")
    public String registerUser(String name, String lastname, String email, String password, int telephoneNumber, String address, Model model) {

        String result = userService.createAccount(name, lastname, email, password, telephoneNumber, address);
        if (result.equals("Your account has been successfully created, enjoy shopping " + name)) {

            return "sneaker_loginpage";
        }
        else  {
            model.addAttribute("result", result);
            return "sneaker_registrationpage";
        }
    }

}
