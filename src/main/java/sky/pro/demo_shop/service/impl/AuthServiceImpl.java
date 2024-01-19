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


import java.util.logging.Logger;


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

    @Override
    public boolean login(LoginDto loginDto) {
        String p = loginDto.getPassword();

        if (userRepository.findByEmailIgnoreCase(loginDto.getUsername()).isEmpty()) {
            log.info(" login отрабатывает false");
            return false;
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginDto.getUsername());
        String p2 = userDetails.getPassword();
        log.info(" login отрабатывает второй блок");

        return passwordEncoder.matches(p, p2);

    }


    @Override
    public boolean register(RegisterDto registerDto) {
        User newUser = userMapperDto.registerDtoToUser(registerDto);


        newUser.setPassword(webSecurityConfig.passwordEncoder().encode(newUser.getPassword()));

        if (userRepository.findByEmailIgnoreCase(newUser.getEmail()).isEmpty()) {
            userRepository.save(newUser);
            return true;
        }

        return false;
    }


}
