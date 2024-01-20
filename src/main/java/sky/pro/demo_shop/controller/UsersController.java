package sky.pro.demo_shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.demo_shop.dto.*;
import sky.pro.demo_shop.service.impl.ImageServiceImpl;
import sky.pro.demo_shop.service.impl.UserServiceImpl;

import javax.transaction.Transactional;
import java.io.IOException;

import java.util.logging.Logger;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping(path = "/users")
@Transactional
public class UsersController {
    private final UserServiceImpl userService;
    private final ImageServiceImpl imageService;
    private static final Logger log = Logger.getLogger(String.valueOf(UsersController.class));

    public UsersController(UserServiceImpl userService, ImageServiceImpl imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @PostMapping(path = "/set_password")
    public ResponseEntity<?> updatePassword(@RequestBody NewPasswordDto newPassword) {


        userService.setPassword(newPassword);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    /**
     * Получение информации об авторизованном пользователе
     *
     * @param
     * @return
     */
    @GetMapping("/me")

    public ResponseEntity<UserDto> getAuthorizedUser() {
//        String authorizedUser = userService.getAuthorizedUser();


        return ResponseEntity.ok(userService.getUser());

    }

    /**
     * Обновление информации об авторизованном пользователе
     *
     * @param updateUser
     * @return
     */

    @PatchMapping(path = "/me")
    public ResponseEntity<UserDto> meUpdate(@RequestBody UpdateUserDto updateUser) {
        userService.updateUser(updateUser);
//        return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.ok(userService.getUser());
    }

    /**
     * Обновление аватара авторизованного пользователя
     *
     * @param image
     * @param
     * @return
     */
    @PatchMapping(path = "/me/image" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> meImage(@RequestBody MultipartFile image) throws IOException {
        userService.setImage(image);
        return ResponseEntity.ok().build();
    }


}