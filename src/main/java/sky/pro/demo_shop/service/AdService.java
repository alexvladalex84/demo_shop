package sky.pro.demo_shop.service;

import org.springframework.web.multipart.MultipartFile;
import sky.pro.demo_shop.dto.*;

/**
 * Сервис для обработки запросов объявлений и комментариев к объявлениям.
 */
public interface AdService {
    /**
     * Метод сервиса для получения DTO со списком всех объявлений. <br>
     */
    AdsDto getAll();

    /**
     * Метод сервиса для обработки нового объявления в форме DTO  с параметрами и {@link MultipartFile} с изображением.
     */
    AdDto addAd(CreateOrUpdateAdDto adDTO, MultipartFile image);

    /**
     *
     * Метод сервиса для получения DTO  c объявлением, имеющим данный id.
     */
    ExtendedAdDto getAd(Integer pk);

    /**
     * Метод сервиса для удаления объявления, имеющего данный id. <br>
     *
     */
    boolean deleteAd(Integer pk);

    /**
     * Метод сервиса для обновления параметров объявления, имеющего данный id, путём обработки DTO
     *
     */
    AdDto updateAd(Integer pk, CreateOrUpdateAdDto createOrUpdateAdDTO);

    /**

     * Метод сервиса для получения списка со всеми объявлениями авторизованного пользователя в форме DTO
     */
    AdsDto getAllMine();

    /**
     * Метод сервиса для обновления изображения объявления, имеющего данный id, путём отправки {@link MultipartFile}.
     */
    String updateAdImage(Integer id, MultipartFile image);

    /**
     * Метод сервиса для получения списка комментариев к объявлению, имеющему данный id, в форме DTO
     */
    CommentsDto getAdComments(Integer pk);

    /**
     * Метод сервиса для обработки нового комментария или обновления старого комментария к объявлению, имеющему данный id, в форме DTO
     */
    CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDTO);

    /**
     * Метод сервиса для удаления комментария, имеющего данный id комментария, к объявлению, имеющему данный id объявления. <br>
     */
    boolean deleteComment(Integer adPk, Integer commentPk);

    /**
     * Метод сервиса для обновления комментария, имеющего данный id комментария, к объявлению, имеющему данный id объявления, путём обработки DTO
     */
    CommentDto updateComment(Integer adPk, Integer commentPk, CreateOrUpdateCommentDto createOrUpdateCommentDTO);
}
