package sky.pro.demo_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sky.pro.demo_shop.dto.CommentDto;
import sky.pro.demo_shop.dto.CreateOrUpdateCommentDto;
import sky.pro.demo_shop.entity.Comment;

@Mapper
public interface CommentMapperDto {

    CommentDto commentToCommentDto(Comment comment);
    Comment commentDtoToComment(CommentDto commentDto);

    CreateOrUpdateCommentDto commentToCreateOrUpdateCommentDto(Comment comment);

    Comment CreateOrUpdateCommentDtoToComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);
}
