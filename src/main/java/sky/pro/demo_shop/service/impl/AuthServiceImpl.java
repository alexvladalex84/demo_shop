package sky.pro.demo_shop.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sky.pro.demo_shop.config.MyUserDetailsService;
import sky.pro.demo_shop.config.WebSecurityConfig;
import sky.pro.demo_shop.dto.LoginDto;
import sky.pro.demo_shop.dto.RegisterDto;
import sky.pro.demo_shop.entity.User;
import sky.pro.demo_shop.mapper.UserMapperDto;
import sky.pro.demo_shop.repository.UserRepository;
import sky.pro.demo_shop.service.AuthService;


import org.apache.log4j.Logger;


@Service
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsService myUserDetailsService;
    private final WebSecurityConfig webSecurityConfig;
    private final PasswordEncoder passwordEncoder;
    private final UserMapperDto userMapperDto;
    private final UserRepository userRepository;

    private static final Logger log = Logger.getLogger(String.valueOf(AuthServiceImpl.class));

    public AuthServiceImpl(MyUserDetailsService myUserDetailsService, WebSecurityConfig webSecurityConfig
            , PasswordEncoder passwordEncoder, UserMapperDto userMapperDto, UserRepository userRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.webSecurityConfig = webSecurityConfig;
        this.passwordEncoder = passwordEncoder;
        this.userMapperDto = userMapperDto;
        this.userRepository = userRepository;
    }

    /**
     * метод для аутентификации пользователей по логину и паролю
     * @param loginDto
     * @return
     */
    @Override
    public boolean login(LoginDto loginDto) {
        String p = loginDto.getPassword();

        if (userRepository.findByEmail(loginDto.getUsername()).isEmpty()) {
            log.error(" login отрабатывает false");
            return false;
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginDto.getUsername());
        String p2 = userDetails.getPassword();


        return passwordEncoder.matches(p, p2);

    }

    /**
     * метод для регистрации пользователя
     * @param registerDto
     * @return
     */
    @Override
    public boolean register(RegisterDto registerDto) {
        User newUser = userMapperDto.registerDtoToUser(registerDto);


        newUser.setPassword(webSecurityConfig.passwordEncoder().encode(newUser.getPassword()));

        if (userRepository.findByEmail(newUser.getEmail()).isEmpty()) {
            userRepository.save(newUser);
            return true;
        }


        return false;
    }


}
