package sky.pro.demo_shop.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface ImageService {

    ResponseEntity<?> getImage(String imageName) throws IOException;
}
