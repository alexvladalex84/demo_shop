package sky.pro.demo_shop.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sky.pro.demo_shop.entity.User;
import sky.pro.demo_shop.repository.UserRepository;

import java.util.Optional;

@Service
@EnableWebSecurity
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> usersOptional = userRepository.findByEmail(username);

        if (usersOptional.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new UserDetailsImpl(usersOptional.get());
    }


}

