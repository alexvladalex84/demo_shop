package sky.pro.demo_shop.mapper;

import org.mapstruct.Mapper;
import sky.pro.demo_shop.dto.LoginDto;
import sky.pro.demo_shop.entity.User;


@Mapper
public interface LoginUser {
    LoginDto loginUserDtoToUser(User user);
}
