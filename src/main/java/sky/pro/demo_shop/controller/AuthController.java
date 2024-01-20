package sky.pro.demo_shop.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.demo_shop.dto.LoginDto;

import sky.pro.demo_shop.dto.RegisterDto;
import sky.pro.demo_shop.entity.Users;
import sky.pro.demo_shop.service.AuthService;


@CrossOrigin(value = "http://localhost:3000")
@RestController

public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

        @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        if (authService.login(loginDto) ){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (authService.register(registerDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
//    @PostMapping(path = "/login")
//    public ResponseEntity<?> login(@RequestBody LoginDto loginDTO) {
//        Users user = authService.login(loginDTO);
//        if (user != null) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setBasicAuth(user.getEmail(), user.getPassword());
//            return ResponseEntity.ok().headers(headers).build();
//        } else {
//            return ResponseEntity.status(401).build();
//        }
//    }
//
//    @PostMapping(path = "/register")
//    public ResponseEntity<?> register(@RequestBody RegisterDto registerDTO) {
//        Users user = authService.register(registerDTO);
//        if (user != null) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setBasicAuth(user.getEmail(), user.getPassword());
//            return ResponseEntity.status(201).headers(headers).build();
//        } else {
//            return ResponseEntity.status(400).build();
//        }
//    }
}