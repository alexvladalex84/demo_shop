package sky.pro.demo_shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Data
@Getter
@Setter
public class LoginDto {
    private String userName;
    private String password;

    public LoginDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDto login = (LoginDto) o;
        return Objects.equals(userName, login.userName) && Objects.equals(password, login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}