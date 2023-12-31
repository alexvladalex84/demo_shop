package sky.pro.demo_shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateUser {
    private String firstName;
    private String lastName;
    private String phone;
}