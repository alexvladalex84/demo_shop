package sky.pro.demo_shop.service.impl;

import org.apache.tika.Tika;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.demo_shop.config.GetAuthenticationUser;
import sky.pro.demo_shop.entity.User;
import sky.pro.demo_shop.exeption.UserNotFoundException;
import sky.pro.demo_shop.repository.UserRepository;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Optional;
import org.apache.log4j.Logger;
import sky.pro.demo_shop.service.ImageService;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


@Service
@Transactional
public class ImageServiceImpl implements ImageService {
//"${avatar.path.to.image.folder}"
//"${ads.path.to.image.folder}"
    private final  String pathToAvatars;

    private final  String pathToImageAds;

    private static final Logger log = Logger.getLogger(String.valueOf(AuthServiceImpl.class));

    public ImageServiceImpl(@Value("${avatar.path.to.image.folder}") String pathToAvatars
            , @Value("${ads.path.to.image.folder}") String pathToImageAds) {
        this.pathToAvatars = pathToAvatars;
        this.pathToImageAds = pathToImageAds;

    }

    @Override
    public ResponseEntity<byte[]> getImage(String imageName) throws IOException {
        Path imagePath = Path.of(pathToAvatars, imageName);
        if (!Files.isExecutable(imagePath)) {
            imagePath = Path.of(pathToImageAds, imageName);
        }
        if (!Files.isExecutable(imagePath)) {
            throw new IOException("Файл пустой");
        }
        Tika tika = new Tika();
        String mimeType = tika.detect(imagePath);
        MediaType mediaType = MediaType.parseMediaType(mimeType);

        byte[] imageInBytes = new byte[(int) Files.size(imagePath)];
        try (
                InputStream is = Files.newInputStream(imagePath)
        ) {
            IOUtils.readFully(is, imageInBytes);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentLength(imageInBytes.length);
        headers.setContentDispositionFormData(imageName, imageName);
        return ResponseEntity.ok().headers(headers).body(imageInBytes);
    }



}
