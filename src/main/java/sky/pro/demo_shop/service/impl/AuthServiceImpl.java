package sky.pro.demo_shop.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import sky.pro.demo_shop.dto.RegisterDto;
import sky.pro.demo_shop.dto.Role;
import sky.pro.demo_shop.entity.Users;
import sky.pro.demo_shop.repository.UserRepository;
import sky.pro.demo_shop.service.AuthService;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

//    @Override
//    public boolean register(RegisterDto register) {
//        if (manager.userExists(register.getUserName())) {
//            return false;
//        }
//        manager.createUser(
//                User.builder()
//                        .passwordEncoder(this.encoder::encode)
//                        .password(register.getPassword())
//                        .username(register.getUserName())
//                        .roles(register.getRole().name())
//                        .build());
//
//
//        return true;
//    }
//@Override
//    public boolean login(String userName, String password) {
//    Optional<Users> userByUserPassword = userRepository.findUserByPassword(password);
//
//        if (userByUserPassword.isEmpty()) {
//            return false;
//        }
//
//    }

    @Override
    public boolean register(RegisterDto registerDto) {
        Optional<Users> userByUserName = userRepository.findUserByUsername(registerDto.getUserName());
        if (userByUserName.isPresent()) {
            return false;
        }
        Users userSave = new Users();
        userSave.setUsername(registerDto.getUserName());
        userSave.setPassword(registerDto.getPassword());
        userSave.setFirstName(registerDto.getFirstName());
        userSave.setLastName(registerDto.getLastName());
        userSave.setPhone(registerDto.getPhone());
//        userSave.setRole(registerDto.getRole());
        userRepository.save(userSave);
        return true;
    }

}
