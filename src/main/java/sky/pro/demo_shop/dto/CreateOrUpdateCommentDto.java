package sky.pro.demo_shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class CreateOrUpdateCommentDto {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
