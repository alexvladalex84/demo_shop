package sky.pro.demo_shop.service;

import org.springframework.web.multipart.MultipartFile;
import sky.pro.demo_shop.dto.NewPasswordDto;
import sky.pro.demo_shop.dto.UpdateUserDto;
import sky.pro.demo_shop.dto.UserDto;

public interface UserService {


    void setPassword(NewPasswordDto newPassword);


    UserDto getUser();


    UpdateUserDto updateUser(UpdateUserDto updateUserDto);


    void setImage(MultipartFile image);
}
