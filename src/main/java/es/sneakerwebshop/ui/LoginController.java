package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-10
 * controller for login attempt and getting login page, front page of website
 */
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

    @GetMapping("sneaker-homepage")
    public String getHomepage() {
        return "sneaker_homepage";
    }

    @GetMapping("sneaker-page")
    public String getLoginPage() {
        return "sneaker_loginpage";
    }


    //Attempt to login
    @PostMapping("sneaker-login-attempt")
    public String tryToLogin(@RequestParam String email, @RequestParam String password, Model model) {
        String result = userService.login(email, password);
        if (result.equals("login granted") && userService.getRole() == 0) {
            return "sneaker_homepage";
        }
        else if (result.equals("login granted") && userService.getRole() == 1) {
            return "sneaker_adminpage";
        }
        else {
            model.addAttribute("result", result);
            return "sneaker_loginpage";
        }
    }

}
