package sky.pro.demo_shop.mapper;

import org.mapstruct.Mapper;
import sky.pro.demo_shop.dto.AdDto;
import sky.pro.demo_shop.dto.AdsDto;
import sky.pro.demo_shop.dto.CreateOrUpdateAdDto;
import sky.pro.demo_shop.dto.ExtendedAdDto;
import sky.pro.demo_shop.entity.Ad;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapperDto.class, CommentMapperDto.class})
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

//        Ad adDtoToAd(AdDto ad);

        CreateOrUpdateAdDto adToCreateOrUpdateAdDto(Ad ad);

        Ad createOrUpdateAdDtoToAd(CreateOrUpdateAdDto createOrUpdateAdDto);

        ExtendedAdDto adToExtendedAdDto(Ad ad);

        Ad extendedAdDtoToAd(ExtendedAdDto extendedAdDto);

        default AdsDto adListToAdsDto(List<Ad> adsList) {
            AdsDto ads = new AdsDto();
            ads.setCount(adsList.size());
            List<AdDto> adsDtoList = new ArrayList<>();
            for (Ad ad : adsList) {
                AdDto adDto = adToAdDto(ad);
                adsDtoList.add(adDto);
            }
            ads.setResult(adsDtoList);
            return ads;
        }


}