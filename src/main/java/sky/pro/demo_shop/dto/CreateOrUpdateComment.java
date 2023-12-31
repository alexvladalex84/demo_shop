package sky.pro.demo_shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateOrUpdateComment {
    private String text;
}