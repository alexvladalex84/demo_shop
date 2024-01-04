package sky.pro.demo_shop.service;


import sky.pro.demo_shop.dto.RegisterDto;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDto register);
}
