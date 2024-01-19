//package sky.pro.demo_shop.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import sky.pro.demo_shop.entity.Image;
//import sky.pro.demo_shop.service.impl.AuthServiceImpl;
//import sky.pro.demo_shop.service.impl.ImageServiceImpl;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.transaction.Transactional;
//import java.io.IOException;
//import java.util.logging.Logger;
//
//@CrossOrigin(value = "http://localhost:3000")
//@RestController
//@Transactional
//public class ImageController {
//    private final ImageServiceImpl imageService;
//    private static final Logger log = Logger.getLogger(String.valueOf(ImageController.class));
//
//    public ImageController(ImageServiceImpl imageService) {
//        this.imageService = imageService;
//    }
//
//
//
//    @GetMapping(path = "/{imageName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
//    public ResponseEntity<byte[]> downloadImage(@Parameter(description = "Ссылка на изображение") @PathVariable String imageName, HttpServletResponse response) {
//        try {
//            return imageService.getImage(imageName, response);
//        } catch (IOException e) {
//
//            return ResponseEntity.status(401).build();
//        }
//    }
//    @GetMapping(path = "/{id}/imageBd")
//    public ResponseEntity<byte[]> imageFromBD(@PathVariable Integer id) {
//        Image image = imageService.findById(id);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(image.getMediaType()));
//        headers.setContentLength(image.getBytes().length);
//
//        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(image.getBytes());
//    }
//    }

