package sky.pro.demo_shop.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.demo_shop.dto.AdDto;
import sky.pro.demo_shop.dto.AdsDto;
import sky.pro.demo_shop.service.AdsServiceImpl;

import javax.transaction.Transactional;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping(path = "/ads")
@Transactional
public class AdsController {
    private final AdsServiceImpl adsService;


    public AdsController(AdsServiceImpl adsService) {
        this.adsService = adsService;
    }

    /**
     * Метод для получения списка всех Dto объявлений
     * @return
     */
    @GetMapping
    public ResponseEntity<AdsDto> getAll() {
        adsService.getAll();
        return ResponseEntity.ok(new AdsDto());
    }

    /**
     * метод для добавления объявления
     * @param ad
     * @return
     */
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdDto> addAd(@RequestBody AdDto ad, @RequestPart("image") MultipartFile image) {
        adsService.addAd(ad,image);
        return ResponseEntity.ok().build();
    }

    /**
     *Получение информации об объявлении
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity getAd(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAd(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }
    /**
     *Обновление информации об объявлении
     */
    @PatchMapping(path = "/{id}")
    public ResponseEntity updateAd(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }
    /**
     *Получение объявлений авторизованного пользователя
     */
    @GetMapping(path = "/me")
    public ResponseEntity getAdAuthorizedUser() {
        return ResponseEntity.ok().build();
    }
    /**
     * Обновление картинки объявления
     * @param
     * @return
     */
    @PatchMapping(path = "/{id}/image")
    public ResponseEntity updateImageAD(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Получение коментариев объявления
     * @param
     * @return
     */
    @GetMapping(path = "/{id}/comments")
    public ResponseEntity getAdComments(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }
    /**
     * Добавление коментария к объявлению
     * @param
     * @return
     */
    @PostMapping(path = "/{id}/comments")
    public ResponseEntity postAdComment(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }
    /**
     * Удаление коментария
     */



    @DeleteMapping(path = "/{adId}/comments/{commentId}")
    public ResponseEntity deleteAdComment(@PathVariable int adId, @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }
    /**
     * Обновление коментария
     * @param
     * @return
     */
    @PatchMapping(path = "/{adId}/comments/{commentId}")
    public ResponseEntity updateAdComment(@PathVariable int adId, @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }
}
