package sky.pro.demo_shop.service;


import sky.pro.demo_shop.dto.LoginDto;
import sky.pro.demo_shop.dto.RegisterDto;

import javax.transaction.Transactional;

public interface AuthService {

    boolean login(LoginDto loginDto);

    boolean register(RegisterDto register);

//    boolean login(String username, String password);
}
