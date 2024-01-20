package sky.pro.demo_shop.controller;

import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.demo_shop.dto.*;
import sky.pro.demo_shop.service.impl.AdServiceImpl;

import javax.validation.Valid;

/**
 *
 * Контроллер для запросов объявлений и комментариев к объявлениям.
 *
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping(path = "/ads")
public class AdsController {
    private final AdServiceImpl adsService;

    public AdsController(AdServiceImpl adsService) {
        this.adsService = adsService;
    }

    /**
     * Метод контроллера для получения DTO со списком всех объявлений. <br>
     */

    @GetMapping
    public ResponseEntity<AdsDto> getAll() {
        return ResponseEntity.ok().body(adsService.getAll());
    }

    /**
     *
     */

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdDto> postAd(@RequestPart("properties") @Valid CreateOrUpdateAdDto ad, @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(201).body(adsService.addAd(ad, image));
    }

    /**
     * Метод контроллера для получения DTO  c объявлением, имеющим данный id. <br>
     */

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExtendedAdDto> getAd(@Parameter(description = "ID объявления", example = "1") @PathVariable Integer id) {
        ExtendedAdDto extendedAdDTO = adsService.getAd(id);
        if (extendedAdDTO != null) {
            return ResponseEntity.status(200).body(extendedAdDTO);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    /**

     * Метод контроллера для удаления объявления, имеющего данный id.

     */

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAd(@Parameter(description = "ID объявления", example = "1") @PathVariable Integer id) {
        if (adsService.deleteAd(id)) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * Метод контроллера для обновления параметров объявления, имеющего данный id, путём отправки DTO
     */

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AdDto> updateAd(@Parameter(description = "ID объявления", example = "1") @PathVariable Integer id
            , @RequestBody CreateOrUpdateAdDto createOrUpdateAdDTO) {
        AdDto adDTO = adsService.updateAd(id, createOrUpdateAdDTO);
        if (adDTO == null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.ok(adDTO);
        }
    }

    /**
     * Метод контроллера для получения списка со всеми объявлениями авторизованного пользователя в форме DTO
     */

    @GetMapping(path = "/me")
    public ResponseEntity<AdsDto> getMe() {
        return ResponseEntity.ok(adsService.getAllMine());
    }

    /**
     * Метод контроллера для обновления изображения объявления, имеющего данный id
     */

    @PatchMapping(path = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> patchAdImage(@RequestPart @Valid Integer id, @RequestBody MultipartFile image) {
        String imageUrl = adsService.updateAdImage(id, image);
        if (imageUrl != null) {
            return ResponseEntity.ok(imageUrl);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * Метод контроллера для получения списка комментариев к объявлению, имеющему данный id, в форме DTO
     */

    @GetMapping(path = "/{id}/comments")
    public ResponseEntity<CommentsDto> getAdComments(@Parameter(description = "ID объявления", example = "1") @PathVariable Integer id) {
        CommentsDto commentsDTO = adsService.getAdComments(id);
        if (commentsDTO != null) {
            return ResponseEntity.ok(commentsDTO);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * Метод контроллера для отправки нового комментария или обновления старого комментария к объявлению, имеющему данный id, в форме DTO
     */

    @PostMapping(path = "/{id}/comments")
    public ResponseEntity<CommentDto> postAdComment(@Parameter(description = "ID объявления", example = "1") @PathVariable Integer id
            , @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDTO) {
        CommentDto commentDTO = adsService.addComment(id, createOrUpdateCommentDTO);
        if (commentDTO != null) {
            return ResponseEntity.ok(commentDTO);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * Метод контроллера для удаления комментария, имеющего данный id комментария, к объявлению, имеющему данный id объявления. <br>
     */

    @DeleteMapping(path = "/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteAdComment(@Parameter(description = "ID объявления", example = "1") @PathVariable Integer adId, @Parameter(description = "ID комментария", example = "1") @PathVariable Integer commentId) {
        if (adsService.deleteComment(adId, commentId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * Метод контроллера для обновления комментария, имеющего данный id комментария, к объявлению, имеющему данный id объявления, путём отправки DTO
     */

    @PatchMapping(path = "/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateAdComment(@Parameter(description = "ID объявления", example = "1") @PathVariable Integer adId, @Parameter(description = "ID комментария", example = "1") @PathVariable Integer commentId
            , @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        CommentDto commentDTO = adsService.updateComment(adId, commentId, createOrUpdateCommentDto);
        if (commentDTO != null) {
            return ResponseEntity.ok(commentDTO);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}