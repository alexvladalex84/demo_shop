package sky.pro.demo_shop.service.impl;

import org.apache.log4j.Logger;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.demo_shop.config.GetAuthenticationUser;
import sky.pro.demo_shop.dto.NewPasswordDto;
import sky.pro.demo_shop.dto.UpdateUserDto;
import sky.pro.demo_shop.dto.UserDto;
import sky.pro.demo_shop.entity.User;
import sky.pro.demo_shop.exeption.UserNotFoundException;
import sky.pro.demo_shop.mapper.UserMapperDto;
import sky.pro.demo_shop.repository.UserRepository;
import sky.pro.demo_shop.service.UserService;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    //    @Value("${avatar.path.to.image.folder}")
    private final String pathToAvatars;
    private final UserMapperDto userMapperDto;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GetAuthenticationUser getAuthenticationUser;

    private static final Logger log = Logger.getLogger(String.valueOf(UserServiceImpl.class));


    public UserServiceImpl(@Value("${avatar.path.to.image.folder}") String pathToAvatars, UserMapperDto userMapperDto
            , UserRepository userRepository, PasswordEncoder passwordEncoder, GetAuthenticationUser getAuthenticationUser) {
        this.pathToAvatars = pathToAvatars;
        this.userMapperDto = userMapperDto;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.getAuthenticationUser = getAuthenticationUser;
    }


    /**
     * метод для изменения пароля
     *
     * @return
     */
    @Override
    public void setPassword(NewPasswordDto newPassword) {

        User user = null;
        try {
            user = userRepository.findByEmail(getAuthenticationUser.getAuthorizedUser()).get();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
            userRepository.save(user);
        }
    }

    /**
     * метод для получения пользователя
     *
     * @return
     */
    @Override
    public UserDto getUser() {
        User user = null;
        try {
            user = userRepository.findByEmail(getAuthenticationUser.getAuthorizedUser()).get();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }
        UserDto userDto;
        if (user != null) {
            userDto = userMapperDto.userToUserDto(user);
        } else {
            return null;
        }
        return userDto;
    }


    /**
     * метод для обновления данных пользователя
     *
     * @return
     */
    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        User user = null;
        try {
            user = userRepository.findByEmail(getAuthenticationUser.getAuthorizedUser()).get();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }
        if (user != null) {
            User userUpdate = userMapperDto.updateUserDtoToUser(updateUserDto);
            user.setFirstName(userUpdate.getFirstName());
            user.setLastName(userUpdate.getLastName());
            user.setPhone(userUpdate.getPhone());
            userRepository.save(user);
        } else {
            return null;
        }
        return userMapperDto.userToUpdateUserDto(user);
    }

    /**
     * Метод сервиса для обновления изображения пользователе путём обработки {@link MultipartFile}. <br>
     */
    @Override
    public void setImage(MultipartFile image) {
        User user = null;
        try {
            user = userRepository.findByEmail(getAuthenticationUser.getAuthorizedUser()).get();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }
        if (user != null) {
            Path filePath = null;
            String fileName = "userAv_" + user.getId() + "." + getExtension(image.getOriginalFilename());
            try {
                filePath = Path.of(pathToAvatars, fileName);
                Files.createDirectories(filePath.getParent());
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            try (
                    InputStream is = image.getInputStream();
                    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                    BufferedInputStream bis = new BufferedInputStream(is, 1024);
                    BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
            ) {
                bis.transferTo(bos);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            user.setNameImage(fileName);
            userRepository.save(user);
        }
    }


    /**
     * Вспомогательный метод для получения расширения файла из его названия. <br>
     */
    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

}


