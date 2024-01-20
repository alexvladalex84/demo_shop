package sky.pro.demo_shop.mapper;

import org.mapstruct.Mapper;
import sky.pro.demo_shop.dto.AdDto;
import sky.pro.demo_shop.dto.CreateOrUpdateAdDto;
import sky.pro.demo_shop.dto.ExtendedAdDto;
import sky.pro.demo_shop.entity.Ad;

@Mapper
public interface AdMapperDto {
    AdDto adToAdDto(Ad ad);
    Ad adDtoToAd(AdDto ad);

    CreateOrUpdateAdDto adToCreateOrUpdateAdDto(Ad ad);
    Ad createOrUpdateAdDtoToAd(CreateOrUpdateAdDto createOrUpdateAdDto);

    ExtendedAdDto adToExtendedAdDto(Ad ad);
    Ad extendedAdDtoToAd(ExtendedAdDto extendedAdDto);
}
