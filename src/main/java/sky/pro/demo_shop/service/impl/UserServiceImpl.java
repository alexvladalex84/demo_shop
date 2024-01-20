package sky.pro.demo_shop.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sky.pro.demo_shop.dto.NewPasswordDto;
import sky.pro.demo_shop.dto.UpdateUserDto;
import sky.pro.demo_shop.dto.UserDto;
import sky.pro.demo_shop.entity.User;
import sky.pro.demo_shop.exeption.UserNotFoundException;
import sky.pro.demo_shop.mapper.UserMapperDto;
import sky.pro.demo_shop.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class UserServiceImpl {
//    @Value("${avatar.path.to.image.folder}")
    private final String pathToAvatars;
    private final UserMapperDto userMapperDto;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger log = Logger.getLogger(String.valueOf(UserServiceImpl.class));


    public UserServiceImpl(@Value("${avatar.path.to.image.folder}")String pathToAvatars, UserMapperDto userMapperDto, UserRepository userRepository
            , PasswordEncoder passwordEncoder) {
        this.pathToAvatars = pathToAvatars;
        this.userMapperDto = userMapperDto;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * метод для доступа к полю email авторизованного пользователя
     *
     * @return
     */
    public String getAuthorizedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void setPassword(NewPasswordDto newPasswordDto) {
        log.info("начало работы setPassword");
        Optional<User> usersOptional = userRepository.findByEmail(getAuthorizedUser());

        if (!newPasswordDto.getCurrentPassword().equals(newPasswordDto.getNewPassword()) && usersOptional.isEmpty()) {
            log.info("setPassword ошибка");
            throw new UserNotFoundException("user не найден или пароли не равны");

        }
        log.info("второй блок setPassword");
        User user = usersOptional.get();
        user.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
        userRepository.save(user);
    }

    public User getUserById(Integer id) {
       return userRepository.findById(id).get();
    }
    public UserDto getUser() {
        Optional<User> user = userRepository.findByEmail(getAuthorizedUser());
        UserDto userDto = userMapperDto.userToUserDto(user.get());

        return userDto;
    }


    public void updateUser(UpdateUserDto updateUserDto) {

        Optional<User> user2 = userRepository.findByEmail(getAuthorizedUser());
        if (user2.isEmpty()) {
            log.info("updateUser не найден");
            throw new UserNotFoundException();
        }
        User userUpDate = user2.get();
        userUpDate.setFirstName(updateUserDto.getFirstName());
        userUpDate.setLastName(updateUserDto.getLastName());
        userUpDate.setPhone(updateUserDto.getPhone());

        userRepository.save(userUpDate);

    }



}


