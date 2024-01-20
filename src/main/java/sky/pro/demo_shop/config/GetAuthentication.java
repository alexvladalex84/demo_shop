package sky.pro.demo_shop.config;


import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sky.pro.demo_shop.entity.User;
import sky.pro.demo_shop.exeption.UserNotFoundException;
@Service
@EnableWebSecurity
public class GetAuthentication {





        public User getAuthenticationUser(String userName) throws UserNotFoundException {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication.getName().equals(userName)){
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                return userDetails.getUser();
            }
            throw new UsernameNotFoundException("Не авторизованный пользователь");
        }
    }

