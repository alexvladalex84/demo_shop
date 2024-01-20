package sky.pro.demo_shop.service;

import org.springframework.security.core.Authentication;
import sky.pro.demo_shop.dto.NewPasswordDto;
import sky.pro.demo_shop.dto.UserDto;

public interface UserService {
    void setPassword(NewPasswordDto newPassword, Authentication authentication);

//    UserDto getUser(Authentication authentication);

//    UpdateUser updateUserInfo(UpdateUser update, Authentication authentication);
//
//    @Transactional
//    void updateUserAvatar(MultipartFile image, Authentication authentication) {
//
//    }
}
