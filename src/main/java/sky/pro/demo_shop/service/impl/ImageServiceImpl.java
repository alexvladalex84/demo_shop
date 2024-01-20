package sky.pro.demo_shop.service.impl;

import org.apache.tika.Tika;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


@Service
@Transactional
public class ImageServiceImpl {


    private final  String pathToAvatars;

    private final  String pathToImageAds;
    private final UserRepository userRepository;
//    private final ImageRepository imageRepository;
    private static final Logger log = Logger.getLogger(String.valueOf(AuthServiceImpl.class));

    public ImageServiceImpl(@Value("${avatar.path.to.image.folder}")String pathToAvatars,
         @Value("${ads.path.to.image.folder}") String pathToImageAds, UserRepository userRepository) {
        this.pathToAvatars = pathToAvatars;
        this.pathToImageAds = pathToImageAds;
        this.userRepository = userRepository;
    }

    /**
     * метод для загрузки фото
     * @param file
     * @throws IOException
     */
    public void updateUserAvatar(MultipartFile file) throws IOException {
    Optional<User> user = userRepository.findByEmail(getAuthorizedUser());

    if (user.isEmpty()) {
        log.info(" updateUserAvatar не найден");
        throw new UserNotFoundException();
    }
    String fileName = "UserAv_" + user.get().getId() + "." + getExtensions(file.getOriginalFilename());

//    String fileName2 = "UserAv_" + user.get().getEmail() ;

    Path filePath = Path.of(pathToAvatars, fileName);

//        Path filePath2 = Path.of(pathToAvatars,fileName2);

    Files.createDirectories(filePath.getParent());         //зоздаёт папку
    Files.deleteIfExists(filePath);                            //удаляет файл если тот существует по определенному адресу


    try (
            InputStream is = file.getInputStream();     //открыть входной паток и читать данные загруженного файла
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);  //место куда данные будем добавлять
            BufferedInputStream bis = new BufferedInputStream(is, 1024); //входной поток (сколько за раз будем забирать)
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);)//выходной поток (сколько за раз будем высыпть)
    {
        bis.transferTo(bos);//передача данных из входного в выходной поток
    }


    User userAv = user.get();
    userAv.setNameImage(fileName);

//    Image image = findImageByUserName(getAuthorizedUser()); //ищем рользователя по Id
//    image.setUsers(userAv);                                   //Пользователь к которому загружаем аватар
//    image.setFilePath(filePath.toString());                  //путь к фалу на диске
//    image.setFileSize(file.getSize());                      //размер файла
//    image.setMediaType(file.getContentType());             //тип контент
//    image.setBytes(generateImagePreview(filePath));       //для хранения в базе данных ,уменьшаем файл
    userRepository.save(userAv);
//    imageRepository.save(image);
}

    /**
     * метод для уменьшения фото
     * @param filePath
     * @return
     * @throws IOException
     */
    private byte[] generateImagePreview(Path filePath) throws IOException {
    try (InputStream is = Files.newInputStream(filePath);
         BufferedInputStream bis = new BufferedInputStream(is, 1024);
         ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

        BufferedImage image = ImageIO.read(bis);

        int height = image.getHeight() / (image.getWidth() / 100);
        BufferedImage preview = new BufferedImage(100, height, image.getType());
        Graphics2D graphics = preview.createGraphics();
        graphics.drawImage(image, 0, 0, 100, height, null);
        graphics.dispose();


        ImageIO.write(preview, getExtensions(filePath.getFileName().toString()), baos);
        return baos.toByteArray();
    }

}

    /**
     * метод для выгрузки фото на фронт
     * @return
     * @throws IOException
     */
    public byte[] getImage() throws IOException {
        Optional<User> user = userRepository.findByEmail(getAuthorizedUser());
        if (user.isEmpty()) {
            log.info(" updateUserAvatar не найден");
            throw new UserNotFoundException();
        }
        Path imagePath = Path.of(pathToAvatars, user.get().getNameImage());
        if (!Files.isExecutable(imagePath)) {                 //если файл является не исполняемым
            imagePath = Path.of(pathToImageAds, user.get().getNameImage());
        }
        if (!Files.isExecutable(imagePath)) {
            throw new IOException("фйл не существует");
        }



        byte[] imageInBytes = new byte[(int) Files.size(imagePath)];
        try (
                InputStream is = Files.newInputStream(imagePath)
        ) {
            IOUtils.readFully(is, imageInBytes);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return imageInBytes;
    }

    public ResponseEntity<byte[]> getImage2(String imageName, HttpServletResponse response) throws IOException {
        Path imagePath = Path.of(pathToAvatars,imageName);
        if (!Files.isExecutable(imagePath)) {
            imagePath = Path.of(pathToImageAds,imageName);
        }
        if (!Files.isExecutable(imagePath)) {
            throw new IOException("File doesn't exist");
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
            log.info(e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentLength(imageInBytes.length);
        headers.setContentDispositionFormData(imageName, imageName);
        return ResponseEntity.ok().headers(headers).body(imageInBytes);
    }

//    public Image findImageByUserName(String userName) {
//       User user = userRepository.findBiEmail(userName).get();
//       Integer userId = user.getId();
//       return imageRepository.findByUsersId(userId).orElse(new Image());
//
//    }

    /**
     * вспомогательный метод для расширения извлечения файла
     * @param filename
     * @return
     */
    private String getExtensions(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    /**
     * метод для доступа к полю email авторизованного пользователя
     *
     * @return
     */
    public String getAuthorizedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
