package sky.pro.demo_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sky.pro.demo_shop.dto.NewPasswordDto;
import sky.pro.demo_shop.dto.RegisterDto;
import sky.pro.demo_shop.dto.Role;


@RestController
@RequestMapping(path = "/users")
public class UsersController {
    @PostMapping(path = "/set_password")
    public ResponseEntity setPassword(@RequestBody NewPasswordDto newPassword) {
        if (newPassword.getCurrentPassword() != null && newPassword.getNewPassword() != null && !newPassword.getNewPassword().isBlank()) {
            return ResponseEntity.ok().build();
        } else if (newPassword.getCurrentPassword() == null) {
            return ResponseEntity.status(401).build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping(path = "/me")
    public ResponseEntity me() {
        RegisterDto user = new RegisterDto();
        user.setFirstName("test ");
        user.setLastName("test ");
        user.setPhone("test ");
        user.setRole(Role.USER);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PatchMapping(path = "/me")
    public ResponseEntity meUpdate() {
        return ResponseEntity.ok().build();
    }
    @PatchMapping(path = "/me/image")
    public ResponseEntity image() {
        return ResponseEntity.ok().build();
    }
}
