package sky.pro.demo_shop.entity;

import sky.pro.demo_shop.dto.AdDto;
import sky.pro.demo_shop.dto.AdsDto;
import sky.pro.demo_shop.dto.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String image;
    @OneToMany
//    @OneToMany(mappedBy = "author")
    private List<Ad> adsList;

    public Users() {

    }

    public Users(Long id, String firstName, String lastName, String username
            , String phone, String password, String email, Role role, String image, List<Ad> adsList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.role = role;
        this.image = image;
        this.adsList = adsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ad> getAdsList() {
        return adsList;
    }

    public void setAdsList(List<Ad> adsList) {
        this.adsList = adsList;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", image='" + image + '\'' +
                ", adsList=" + adsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(firstName, users.firstName) && Objects.equals(lastName, users.lastName) && Objects.equals(username, users.username) && Objects.equals(phone, users.phone) && Objects.equals(password, users.password) && Objects.equals(email, users.email) && role == users.role && Objects.equals(image, users.image) && Objects.equals(adsList, users.adsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, phone, password, email, role, image, adsList);
    }
}
