package sky.pro.demo_shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * объявление
 */
@Entity
public class Ad {
    private Integer author;
    private String image; //ссылка на картинку
    @Id
    private Integer pk;   //id объявления
    private Integer price;
    private String title;  //заголовок объявления

    public Ad(Integer author, String image, Integer pk, Integer price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public Integer getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public Integer getPk() {
        return pk;
    }

    public Integer getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "author=" + author +
                ", image='" + image + '\'' +
                ", pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(author, ad.author) && Objects.equals(image, ad.image) && Objects.equals(pk, ad.pk) && Objects.equals(price, ad.price) && Objects.equals(title, ad.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }
}
