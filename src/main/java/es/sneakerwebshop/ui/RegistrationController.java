package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-10
 * controller for registering attempt
 */
import es.sneakerwebshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String registerUser(@RequestParam String name,
                               @RequestParam String lastname,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam int telephoneNumber,
                               @RequestParam String address,
                               @RequestParam String repeatPassword,
                               @RequestParam String repeatEmail,
                               Model model) {

        String result = userService.createAccount(name, lastname, email, password, telephoneNumber, address);
        if (result.equals("Your account has been successfully created, enjoy shopping " + name) && repeatEmail.equals(email) && repeatPassword.equals(password)) {
            return "sneaker_loginpage";
        }
        else  {
            model.addAttribute("result", result);
            return "sneaker_registrationpage";
        }
    }

}
