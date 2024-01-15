package sky.pro.demo_shop.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sky.pro.demo_shop.config.MyUserDetailsService;
import sky.pro.demo_shop.config.WebSecurityConfig;
import sky.pro.demo_shop.dto.LoginDto;
import sky.pro.demo_shop.dto.RegisterDto;
import sky.pro.demo_shop.entity.Users;
import sky.pro.demo_shop.mapper.UserMapperDto;
import sky.pro.demo_shop.repository.UserRepository;
import sky.pro.demo_shop.service.AuthService;

import javax.transaction.Transactional;


@Service
public class AuthServiceImpl implements AuthService {
    private final MyUserDetailsService myUserDetailsService;
    private final WebSecurityConfig webSecurityConfig;
    private final PasswordEncoder passwordEncoder;
    private final UserMapperDto userMapperDto;
    private final UserRepository userRepository;

    public AuthServiceImpl(MyUserDetailsService myUserDetailsService, WebSecurityConfig webSecurityConfig
            , PasswordEncoder passwordEncoder, UserMapperDto userMapperDto, UserRepository userRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.webSecurityConfig = webSecurityConfig;
        this.passwordEncoder = passwordEncoder;
        this.userMapperDto = userMapperDto;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public boolean login(LoginDto loginDto) {

        Users newUser = userMapperDto.loginDtoToUsers(loginDto);
        newUser.setPassword(webSecurityConfig.passwordEncoder().encode(newUser.getPassword()));
        if (userRepository.findByEmail(newUser.getEmail()).isEmpty()) {
            return false;
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(newUser.getUsername());
        if( webSecurityConfig.passwordEncoder().matches(newUser.getPassword(), userDetails.getPassword())){
            return true;}
        return false;
    }
/// @Override
//    @Transactional
//    public Users login(LoginDto loginDTO) {
//        Users loginUser = userMapper.loginDtoToUsers(loginDTO);
//        loginUser.setPassword(encoderConfiguration.passwordEncoder().encode(loginUser.getPassword()));
//        Users user;
//        try {
//            user = userRepository.findByEmail(loginUser.getEmail()).get();
//        } catch (Exception e) {
//            return null;
//        }
//        if (encoderConfiguration.passwordEncoder().matches(loginDTO.getPassword(), user.getPassword())) {
//            loadUserByUsername(user.getEmail());
//            return user;
//        }
//        return null;
    @Override
    public boolean register(RegisterDto registerDto) {
        Users newUser = userMapperDto.registerDtoToUser(registerDto);
        newUser.setPassword(webSecurityConfig.passwordEncoder().encode(newUser.getPassword()));
        if (userRepository.findByEmail(newUser.getEmail()).isEmpty()) {
            userRepository.save(newUser);
            return true;
        }

        return false;
    }


}
