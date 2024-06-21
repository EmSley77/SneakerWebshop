package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-13
 * controller class responisble for user account
 */

import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    private UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("sneaker-account")
    public String getUserAccount(Model model) {
        User account = userService.getAccount();
        if (account != null) {
            model.addAttribute("account", account);
            return "sneaker_accountpage";
        } else {
            return "sneaker_homepage";
        }

    }

    //make user an admin
    @PostMapping("sneaker-make-user-admin")
    public String makeUserAdmin(@RequestParam int userId, Model model) {
        String result = userService.makeUserAdmin(userId);
        if (result.equals("user has become an admin")) {
            model.addAttribute("makeadmin", result);
            return "sneaker_admin_getuserpage";
        }
        else {
            model.addAttribute("makeadmin", result);
            return "sneaker_admin_getuserpage";
        }

    }

    @PostMapping("sneaker-account-update")
    public String updateAccount(Model model,
                                @RequestParam(required = false) String newName,
                                @RequestParam(required = false) String newLastname,
                                @RequestParam(required = false) String newEmail,
                                @RequestParam(required = false) String repeatEmail,
                                @RequestParam(required = false) String newPassword,
                                @RequestParam(required = false) String repeatPassword,
                                @RequestParam(required = false) Integer newTelephoneNumber,
                                @RequestParam(required = false) String newAddress) {
        String result = userService.editAccount(newName, newLastname, newEmail, newPassword, newTelephoneNumber, newAddress, repeatPassword, repeatEmail);
        if (result.equals("successfully updated account information")) {
            model.addAttribute("result", result);
            model.addAttribute("account", userService.getAccount());
            return "sneaker_accountpage";
        } else {
            model.addAttribute("result", result);
            return "sneaker_accountpage";
        }


    }
}
