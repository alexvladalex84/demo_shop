package sky.pro.demo_shop.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetAuthenticationUser {
    /**
     * метод для доступа к полю email авторизованного пользователя
     *
     * @return
     */

    public String getAuthorizedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
