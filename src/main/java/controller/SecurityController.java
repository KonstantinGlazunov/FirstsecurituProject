package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SecurityController {
    @GetMapping("/login")
    public String loginResponse(){
        return "Login";
    }
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin!";
    }
    @GetMapping("/user")
    public String userEndpoint() {
        return "User!";
    }
}