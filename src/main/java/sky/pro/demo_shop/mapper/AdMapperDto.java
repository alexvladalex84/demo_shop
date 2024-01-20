package sky.pro.demo_shop.mapper;

import org.mapstruct.Mapper;
import sky.pro.demo_shop.dto.AdDto;
import sky.pro.demo_shop.dto.AdsDto;
import sky.pro.demo_shop.dto.CreateOrUpdateAdDto;
import sky.pro.demo_shop.dto.ExtendedAdDto;
import sky.pro.demo_shop.entity.Ad;
import sky.pro.demo_shop.entity.User;
import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface AdMapperDto {
    default AdDto adToAdDto(Ad ad) {
        AdDto adDto = new AdDto();
        adDto.setAuthor(ad.getAuthor().getId());
        adDto.setImage("/" + ad.getImage());
        adDto.setPk(ad.getPk());
        adDto.setPrice(ad.getPrice());
        adDto.setTitle(ad.getTitle());
        return adDto;
    }

    default AdsDto adsListToAdsDto(List<Ad> adsList) {
        AdsDto ads = new AdsDto();
        ads.setCount(adsList.size());
        List<AdDto> adsDtoList = new ArrayList<>();
        for (Ad ad : adsList) {
            AdDto adDto = adToAdDto(ad);
            adsDtoList.add(adDto);
        }
        ads.setResults(adsDtoList);
        return ads;
    }

    Ad createOrUpdateAdDtoToAd(CreateOrUpdateAdDto createOrUpdateAd);

    CreateOrUpdateAdDto adToCreateOrUpdateDto(Ad ad);

    default Ad extendedAdDtoToAd(ExtendedAdDto extendedAd, User author) {
        Ad ad = new Ad(extendedAd.getPk(), author, extendedAd.getImage(), extendedAd.getPrice(), extendedAd.getTitle(), extendedAd.getDescription());
        return ad;
    }

    default ExtendedAdDto adToExtendedAd(Ad ad, User author) {
        ExtendedAdDto extendedAd = new ExtendedAdDto();
        extendedAd.setPk(ad.getPk());
        extendedAd.setAuthorFirstName(author.getFirstName());
        extendedAd.setAuthorLastName(author.getLastName());
        extendedAd.setDescription(ad.getDescription());
        extendedAd.setEmail(author.getEmail());
        extendedAd.setImage("/" + ad.getImage());
        extendedAd.setPhone(author.getPhone());
        extendedAd.setPrice(ad.getPrice());
        extendedAd.setTitle(ad.getTitle());
        return extendedAd;
    }
}
