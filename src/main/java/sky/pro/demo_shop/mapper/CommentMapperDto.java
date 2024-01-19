package sky.pro.demo_shop.mapper;

import org.mapstruct.Mapper;
import sky.pro.demo_shop.dto.CommentDto;
import sky.pro.demo_shop.dto.CommentsDto;
import sky.pro.demo_shop.entity.Comment;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapperDto {
    default CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthor(comment.getAuthor().getId());
        commentDto.setAuthorImage("/" + comment.getAuthorImage());
        commentDto.setAuthorFirstName(comment.getAuthorFirstName());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setPk(comment.getPk());
        commentDto.setText(comment.getText());
        return commentDto;
    }

    default CommentsDto commentListToCommentsDto(List<Comment> commentList) {
        CommentsDto comments = new CommentsDto();
        comments.setCount(commentList.size());
        List<CommentDto> commentDTODtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDto commentDto = commentToCommentDto(comment);
            commentDTODtoList.add(commentDto);
        }
        comments.setResults(commentDTODtoList);
        return comments;
    }
}
