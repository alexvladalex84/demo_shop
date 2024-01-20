package sky.pro.demo_shop.service.impl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.demo_shop.config.GetAuthenticationUser;
import sky.pro.demo_shop.dto.*;
import sky.pro.demo_shop.entity.Ad;
import sky.pro.demo_shop.entity.Comment;
import sky.pro.demo_shop.entity.User;
import sky.pro.demo_shop.mapper.AdMapperDto;
import sky.pro.demo_shop.mapper.CommentMapperDto;
import sky.pro.demo_shop.repository.AdRepository;
import sky.pro.demo_shop.repository.CommentRepository;
import sky.pro.demo_shop.repository.UserRepository;
import sky.pro.demo_shop.service.AdService;


import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Реализация сервиса для обработки запросов объявлений и комментариев к объявлениям
 */
@Service
@Transactional
public class AdServiceImpl implements AdService {
//    @Value("${ads.path.to.image.folder}")
    private final String imageDir;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdMapperDto adMapper;
    private final CommentMapperDto commentMapper;
    private final GetAuthenticationUser getAuthenticationUser;
    private static final Logger log = Logger.getLogger(AdServiceImpl.class);

    public AdServiceImpl( @Value("${ads.path.to.image.folder}")String imageDir, AdRepository adRepository, CommentRepository commentRepository
            , UserRepository userRepository, AdMapperDto adMapper, CommentMapperDto commentMapper,GetAuthenticationUser getAuthenticationUser) {
        this.imageDir = imageDir;
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
        this.commentMapper = commentMapper;
        this.getAuthenticationUser = getAuthenticationUser;
    }

    /**
     * Метод сервиса для получения DTO со списком всех объявлений.
     */
@Override
    public AdsDto getAll() {
        List<Ad> adsList;
        try {
            adsList = adRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        if (!adsList.isEmpty()) {
            return adMapper.adsListToAdsDto(adsList);
        } else {
            return null;
        }
    }

    /**
     * Метод сервиса для обработки нового объявления в форме DTO с параметрами и {@link MultipartFile} с изображением.
     */
@Override
    public AdDto addAd(CreateOrUpdateAdDto adDTO, MultipartFile image) {
        User user = getUser();
        if (user == null) {
            return null;
        }
        Ad ad = adMapper.createOrUpdateAdDtoToAd(adDTO);
        ad.setAuthor(user);
        ad = adRepository.save(ad);
        Path filePath = null;
        String fileName = "user_" + user.getId() + "_ad_" + ad.getPk() + "." + getExtension(image.getOriginalFilename());
        try {
            filePath = Path.of(imageDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        ad.setImage(fileName);
        ad = adRepository.save(ad);
        return adMapper.adToAdDto(ad);
    }


    /**
     * Вспомогательный метод для получения расширения файла из его названия.
     */
    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    /**
     * Метод сервиса для получения DTO  c объявлением, имеющим данный id.
     */
    @Override
    public ExtendedAdDto getAd(Integer pk) {
        Ad ad = null;
        try {
            ad = adRepository.findByPk(pk).get();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }
        if (ad != null) {
            return adMapper.adToExtendedAd(ad, ad.getAuthor());
        } else {
            return null;
        }
    }

    /**
     * <br>
     * Метод сервиса для удаления объявления, имеющего данный id. <br>
     */
    @Override
    public boolean deleteAd(Integer pk) {
        User user = getUser();
        Ad ad = getThatAd(pk);
        if (user != null && ad != null) {
            user = userCheck(user, ad);
        } else {
            return false;
        }
        if (user == null) {
            return false;
        }
        try {
            adRepository.delete(ad);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * <br>
     * Метод сервиса для обновления параметров объявления, имеющего данный id, путём обработки DTO
     */
    @Override
    public AdDto updateAd(Integer pk, CreateOrUpdateAdDto createOrUpdateAdDTO) {
        User user = getUser();
        Ad adUpdate = adMapper.createOrUpdateAdDtoToAd(createOrUpdateAdDTO);
        Ad ad = getThatAd(pk);
        user = userCheck(user, ad);
        if (ad != null && user != null) {
            ad.setTitle(adUpdate.getTitle());
            ad.setPrice(adUpdate.getPrice());
            ad.setDescription(adUpdate.getDescription());
            ad = adRepository.save(ad);
        } else {
            return null;
        }
        return adMapper.adToAdDto(ad);
    }

    /**
     * <br>
     * Метод сервиса для получения списка со всеми объявлениями авторизованного пользователя в форме DTO
     * <br>
     */
    @Override
    public AdsDto getAllMine() {
        User user = getUser();
        if (user == null) {
            return null;
        }
        List<Ad> adsList;
        try {
            adsList = adRepository.findByAuthor(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return adMapper.adsListToAdsDto(adsList);
    }

    /**
     * <br>
     * Метод сервиса для обновления изображения объявления, имеющего данный id, путём отправки {@link MultipartFile}. <br>
     * <br>
     */
    @Override
    public String updateAdImage(Integer pk, MultipartFile image) {
        User user = getUser();
        Ad ad = getThatAd(pk);
        if (ad != null && ad.getAuthor().equals(user)) {
            Path filePath = null;
            String fileName = "userAd_" + user.getId() + "_ad_" + ad.getPk() + "." + getExtension(image.getOriginalFilename());
            try {
                filePath = Path.of(imageDir, fileName);
                Files.createDirectories(filePath.getParent());
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            try (
                    InputStream is = image.getInputStream();
                    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                    BufferedInputStream bis = new BufferedInputStream(is, 1024);
                    BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
            ) {
                bis.transferTo(bos);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            ad.setImage(fileName);
            adRepository.save(ad);
            return String.valueOf(filePath);
        } else {
            return null;
        }
    }

    /**
     * <br>
     * Метод сервиса для получения списка комментариев к объявлению, имеющему данный id, в форме DTO
     */
    @Override
    public CommentsDto getAdComments(Integer pk) {
        Ad ad = getThatAd(pk);
        if (ad == null) {
            return null;
        }
        List<Comment> commentsList = commentRepository.findByAd(ad);
        if (commentsList.isEmpty()) {
            CommentsDto commentsDTO = new CommentsDto();
            commentsDTO.setCount(0);
            CommentDto commentDTO = new CommentDto();
            List<CommentDto> commentDTOList = new ArrayList<>(List.of(commentDTO));
            commentsDTO.setResults(commentDTOList);
            return commentsDTO;
        } else {
            return commentMapper.commentListToCommentsDto(commentsList);
        }
    }

    /**
     * <br>
     * Метод сервиса для обработки нового комментария или обновления старого комментария к объявлению, имеющему данный id, в форме DTO
     */
    @Override
    public CommentDto addComment(Integer pk, CreateOrUpdateCommentDto createOrUpdateCommentDTO) {
        User user = getUser();
        Ad ad = getThatAd(pk);
        if (user == null || ad == null) {
            return null;
        }
        Comment comment = new Comment(null, user, user.getNameImage(), user.getFirstName(), new Date().getTime(), createOrUpdateCommentDTO.getText(), ad);
        comment = commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }

    /**
     * <br>
     * Метод сервиса для удаления комментария, имеющего данный id комментария, к объявлению, имеющему данный id объявления. <br>
     * <br>
     */
    @Override
    public boolean deleteComment(Integer adPk, Integer commentPk) {
        User user = getUser();
        Ad ad = getThatAd(adPk);
        if (user == null || ad == null) {
            return false;
        }
        Comment comment = getComment(commentPk);
        if (comment != null) {
            user = userCheck(user, comment);
        } else {
            return false;
        }
        if (user == null) {
            return false;
        }
        commentRepository.delete(comment);
        return true;
    }

    /**
     * <br>
     * Метод сервиса для обновления комментария, имеющего данный id комментария, к объявлению, имеющему данный id объявления, путём обработки DTO {@link CreateOrUpdateCommentDTO}. <br>
     */
    @Override
    public CommentDto updateComment(Integer adPk, Integer commentPk, CreateOrUpdateCommentDto createOrUpdateCommentDTO) {
        User user = getUser();
        Ad ad = getThatAd(adPk);
        if (user == null || ad == null) {
            return null;
        }
        Comment comment = null;
        try {
            comment = commentRepository.findByPk(commentPk).get();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }
        if (comment == null) {
            comment = new Comment(commentPk, user, user.getNameImage(), user.getFirstName(), new Date().getTime(), createOrUpdateCommentDTO.getText(), ad);
        } else {
            user = userCheck(user, comment);
            if (user == null) {
                return null;
            }
            comment.setText(createOrUpdateCommentDTO.getText());
        }
        comment = commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }

    /**
     * <br>
     * Вспомогательный метод для получения сущности авторизованного {@link User} из БД. <br>
     * <br>
     */
    private User getUser() {
        User user = null;
        try {
            user = userRepository.findByEmail(getAuthenticationUser.getAuthorizedUser()).get();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }
        return user;
    }

    /**
     * <br>
     * Вспомогательный метод для получения сущности объявления {@link Ad} из БД по его id. <br>
     * <br>
     */
    private Ad getThatAd(Integer pk) {
        Ad ad;
        try {
            ad = adRepository.findByPk(pk).get();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return null;
        }
        return ad;
    }

    /**
     * <br>
     * Вспомогательный метод для получения сущности объявления {@link Comment} из БД по его id. <br>
     * <br>
     */
    private Comment getComment(Integer pk) {
        Comment comment;
        try {
            comment = commentRepository.findByPk(pk).get();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return null;
        }
        return comment;
    }

    /**
     * <br>
     * Вспомогательный метод для проверки, что авторизованный {@link User} является автором {@link Ad} или имеет {@link Role#ADMIN}. <br>
     * <br>
     */
    private User userCheck(User user, Ad ad) {
        if (user.getRole().equals(Role.USER) && ad.getAuthor().equals(user) || user.getRole().equals(Role.ADMIN)) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * <br>
     * Вспомогательный метод для проверки, что авторизованный {@link User} является автором {@link Comment} или имеет {@link Role#ADMIN}. <br>
     * <br>
     */
    private User userCheck(User user, Comment comment) {
        if (user.getRole().equals(Role.USER) && comment.getAuthor().equals(user) || user.getRole().equals(Role.ADMIN)) {
            return user;
        } else {
            return null;
        }
    }
}