package sky.pro.demo_shop.entity;



import sky.pro.demo_shop.dto.AdDto;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * объявление
 */
@Entity
public class Ad {
//    @OneToMany
    private String image; //ссылка на картинку
    @Id
    private Integer pk;   //id объявления
    private Integer price;
    private String title;  //заголовок объявления
    private String description;        //описание
    private int count;     //общее количество объявлений
//    @ManyToOne(mappedBy = "adsList")
    private Integer author;  // id автора объявления
    @ManyToOne
    private Users users;
    @OneToMany
    private List<Comment> comments;

    public Ad() {

    }

    public Ad(String image, Integer pk, Integer price, String title
            , String description, int count, Integer author, List<Comment> comments) {
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
        this.description = description;
        this.count = count;
        this.author = author;
        this.comments = comments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "image='" + image + '\'' +
                ", pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", count=" + count +
                ", author=" + author +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return count == ad.count && Objects.equals(image, ad.image) && Objects.equals(pk, ad.pk) && Objects.equals(price, ad.price) && Objects.equals(title, ad.title) && Objects.equals(description, ad.description) && Objects.equals(author, ad.author) && Objects.equals(comments, ad.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, pk, price, title, description, count, author, comments);
    }
}
