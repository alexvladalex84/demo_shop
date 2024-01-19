package sky.pro.demo_shop.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.demo_shop.dto.AdDto;
import sky.pro.demo_shop.dto.AdsDto;
import sky.pro.demo_shop.entity.Ad;
import sky.pro.demo_shop.mapper.AdMapperDto;
import sky.pro.demo_shop.repository.AdRepository;
//import sky.pro.demo_shop.repository.ImageRepository;
import sky.pro.demo_shop.repository.UserRepository;

import java.util.List;

@Service
public class AdsServiceImpl {
    private final AdRepository adRepository;
    private final AdMapperDto adMapperDto;
    private final UserRepository userRepository;

    public AdsServiceImpl(AdRepository adRepository, AdMapperDto adMapperDto, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.adMapperDto = adMapperDto;
        this.userRepository = userRepository;
    }

    /**
     * Метод для получения списка всех Dto объявлений
     * @return
     */
    public AdsDto getAll() {
        List<Ad> adsList;
        try {
            adsList = adRepository.findAll();
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
        if (!adsList.isEmpty()) {
            return adMapperDto.adsListToAdsDto(adsList);
        } else {
            return null;
        }
    }


    public ResponseEntity addAd(AdDto adDto, MultipartFile image) {

        Ad ad = new Ad();
        ad.setPk(adDto.getPk());
//        ad.set(adDto.getAuthor());
        ad.setPrice(adDto.getPrice());
        ad.setTitle(adDto.getTitle());
        ad.setImage(adDto.getImage());
        adRepository.save(ad);
        return ResponseEntity.ok().build();
    }

}
