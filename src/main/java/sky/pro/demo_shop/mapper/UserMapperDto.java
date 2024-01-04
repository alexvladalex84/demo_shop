package sky.pro.demo_shop.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sky.pro.demo_shop.dto.LoginDto;
import sky.pro.demo_shop.dto.RegisterDto;
import sky.pro.demo_shop.dto.UpdateUserDto;
import sky.pro.demo_shop.dto.UserDto;
import sky.pro.demo_shop.entity.Users;

@Mapper
public interface UserMapperDto {
    UserDto userToUserDto(Users user);

    Users UserDtoToUsers(UserDto userDto);

    UpdateUserDto userToUpdateUserDto (Users user);

    Users updateUserDtoToUser (UpdateUserDto updateUserDto);



    @Mapping(source = "email",target = "userName")
    RegisterDto userToRegisterDto(Users users);

    @Mapping(source = "userName",target = "email")
    Users registerDtoToUser(RegisterDto registerDto);

    @Mapping(source = "email", target = "username")
    LoginDto usersToLoginDto(Users users);

    @Mapping(source = "username",target = "email")
    Users loginDtoToUsers(LoginDto loginDto);
}
