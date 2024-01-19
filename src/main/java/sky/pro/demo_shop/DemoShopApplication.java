package sky.pro.demo_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import sky.pro.demo_shop.entity.Users;
import sky.pro.demo_shop.repository.UserRepository;

import java.util.Optional;


@SpringBootApplication

public class DemoShopApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoShopApplication.class, args);
	}


}
