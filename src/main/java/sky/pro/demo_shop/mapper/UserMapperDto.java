package sky.pro.demo_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sky.pro.demo_shop.dto.*;
import sky.pro.demo_shop.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapperDto {
    default UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDTO = new UserDto();
        if (user.getId() != null) {
            userDTO.setId(user.getId());
        }
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        userDTO.setImage("/" + user.getNameImage());
        return userDTO;
    }

    User userDtoToUser(UserDto user);

    User updateUserDtoToUser(UpdateUserDto updateUser);

    UpdateUserDto userToUpdateUserDto(User user);

    @Mapping(target = "email", source = "register.username")
    User registerDtoToUser(RegisterDto register);

    @Mapping(target = "firstName", source = "extendedAd.authorFirstName")
    @Mapping(target = "lastName", source = "extendedAd.authorLastName")
    @Mapping(target = "nameImage", ignore = true)
    User extendedAdToUser(ExtendedAdDto extendedAd);

    @Mapping(target = "username", source = "user.email")
    LoginDto userToLoginDto(User user);

    @Mapping(target = "email", source = "loginDTO.username")
    User loginDtoToUser(LoginDto loginDTO);
}
