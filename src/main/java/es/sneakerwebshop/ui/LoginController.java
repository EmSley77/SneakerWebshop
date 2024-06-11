package es.sneakerwebshop.ui;

import es.sneakerwebshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("sneaker-page")
    public String getLoginPage() {
        return "sneaker_loginpage";
    }


    //Attempt to login
    @PostMapping("sneaker-login-attempt")
    public String tryToLogin(@RequestParam String email, @RequestParam String password, Model model) {
        String result = userService.login(email, password);
        if (result.equals("login granted")) {
            return "sneaker_homepage";
        }

        else {
            model.addAttribute("result", result);
            return "sneaker_loginpage";
        }
    }

}
