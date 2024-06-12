package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Controller for admin
 */

import es.sneakerwebshop.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {


    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("sneaker-register-admin")
    public String registerAdmin(
            @RequestParam String name,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam int telephoneNumber,
            @RequestParam String address,
            @RequestParam String repeatPassword,
            @RequestParam String repeatEmail,
            Model model) {

        String result = adminService.createAdminAccount(name, lastname, email, password, telephoneNumber, address);
        if (result.equals("Admin added") && repeatEmail.equals(email) && repeatPassword.equals(password)) {
            return "sneaker_adminpage";
        } else {
            model.addAttribute("result", result);
            return "sneaker_adminpage";
        }
    }

}
