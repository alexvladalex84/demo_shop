package sky.pro.demo_shop.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sky.pro.demo_shop.DemoShopApplication;
import sky.pro.demo_shop.dto.RegisterDto;
import sky.pro.demo_shop.dto.Role;
import sky.pro.demo_shop.dto.UserDto;
import sky.pro.demo_shop.entity.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest2 {
    @Autowired
    private TestRestTemplate restTemplate;         //выполняет запросы к приложению
    @LocalServerPort
    private int port;                                 //новый порт на котором будет работать тест приложение


    @Autowired
    private UsersController usersController;   //проверяемый контроллер
    @Autowired
    private AuthController authController;

    @Test
    public void contextLoads() throws Exception {             //контроллер создался и существует

        Assertions.assertThat(usersController).isNotNull();
    }
/*private String username;
    private String password;
    private String firstName;
    private String lastName;

    private String phone;
    private Role role;*/
//    @Test
//    void registerTest() {
//        RegisterDto registerDto = new RegisterDto();
//        registerDto.setUsername("user@gmail.com");
//        registerDto.setPassword("123321qweewq");
//        registerDto.setFirstName("Max");
//        registerDto.setLastName("Max");
//        registerDto.setPhone("+79219702455");
//        registerDto.setRole(Role.USER);
//
//
//     HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////        personJsonObject = new JSONObject();
////        personJsonObject.put("id", 1);
////        personJsonObject.put("name", "John");
////       create   post запрос
//
//        ResponseEntity<RegisterDto> responseCreate = restTemplate.postForEntity("/register",headers.get(registerDto)
//
//                ,RegisterDto.class);//Student.class возвращаемый класс
//
//        Assertions.assertThat(responseCreate).isNotNull();
//        Assertions.assertThat(responseCreate.getStatusCode()).isEqualTo(HttpStatus.CREATED);//проверяем статус 201
//
//        RegisterDto respBody = responseCreate.getBody();// переменная с телом запроса
//
//        Assertions.assertThat(respBody).isNotNull();
//
//        Assertions.assertThat(respBody.getUsername()).isNotNull();
////        Assertions.assertThat(respBody.getName()).isNotNull();
////        Assertions.assertThat(respBody.getAge()).isNotNull();
////        Assertions.assertThat(respBody.getName()).isEqualTo("Bob");
////        Assertions.assertThat(respBody.getAge()).isEqualTo(20);
//
//    }
//    @Test
//    void getAuthorizedUser() throws Exception {
//
//        ResponseEntity<UserDto> responseGet = restTemplate.getForEntity("/user/me" , UserDto.class);
//
//        Assertions.assertThat(responseGet).isNotNull();
//
//        Assertions.assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.OK);
////        Student respBody1 = responseGet.getBody();// переменная с телом запроса
////        Assertions.assertThat(respBody1).isNotNull();
////        Assertions.assertThat(respBody1.getId()).isNotNull();
////        Assertions.assertThat(respBody1.getName()).isEqualTo("Bob");
////        Assertions.assertThat(respBody1.getAge()).isEqualTo(20);
//    }
///*registerDto.getUsername()
//                +registerDto.getPassword()
//                +registerDto.getFirstName()
//                +registerDto.getLastName()
//                +registerDto.getPhone()
//                +registerDto.getRole()*/
}
