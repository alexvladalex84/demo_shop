package sky.pro.demo_shop.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sky.pro.demo_shop.entity.Users;
import sky.pro.demo_shop.repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> usersOptional = userRepository.findByEmailIgnoreCase(username);
        if (usersOptional.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new UserDetailsImpl(usersOptional.get());
    }
}

