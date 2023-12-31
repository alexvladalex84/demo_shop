package sky.pro.demo_shop.mapper;

import org.mapstruct.Mapper;
import sky.pro.demo_shop.dto.Register;
import sky.pro.demo_shop.entity.User;


@Mapper
public interface RegisterUser {
    Register registerUserDtoToUser(User user);
}
