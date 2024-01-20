package sky.pro.demo_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sky.pro.demo_shop.filter.BasicAuthCorsFilter;


import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    private final MyUserDetailsService userDetailsService;
    private final BasicAuthCorsFilter basicAuthCorsFilter;
    public Argon2PasswordEncoder passwordEncoder;

    public WebSecurityConfig(MyUserDetailsService userDetailsService, BasicAuthCorsFilter basicAuthCorsFilter) {
        this.userDetailsService = userDetailsService;
        this.basicAuthCorsFilter = basicAuthCorsFilter;
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login", "/register",
            "/ads",
            "/ads/*/image",
            "/users/*/image"
    };

//    @Bean
//    public JdbcUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//
//        return new JdbcUserDetailsManager();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers("/", "/ads", "/login", "/register", "/swagger-resources/**", "/swagger-ui.html", "/v3/api-docs", "/webjars/**")
                                        .permitAll()
                                        .mvcMatchers("/ads/**", "/users/**")
                                        .authenticated())
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .cors()
                .and()
                .addFilterBefore(basicAuthCorsFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
