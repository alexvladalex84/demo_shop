package sky.pro.demo_shop.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.demo_shop.service.impl.ImageServiceImpl;

import javax.transaction.Transactional;
import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@Transactional
public class ImageController {
    private final ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }



    @GetMapping(path = "/{imageName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> downloadImage (@PathVariable String imageName) {
        try {
            return imageService.getImage(imageName);
        } catch (IOException e) {

            return ResponseEntity.status(401).build();
        }
    }
  /*  @GetMapping(path = "/{id}/imageBd")
    public ResponseEntity<byte[]> imageFromBD(@PathVariable Integer id) {
        Image image = imageService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getMediaType()));
        headers.setContentLength(image.getBytes().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(image.getBytes());
      }
   */

    }

