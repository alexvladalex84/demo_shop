package sky.pro.demo_shop.entity;




import javax.persistence.*;
import java.util.Objects;

/**
 * объявление
 */
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private int price;

    private String title;



    @OneToOne
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "author_id")
    private Users author;

    public Ad(long id, String description, int price, String title, Image image, Users author) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.title = title;
        this.image = image;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", image=" + image +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return id == ad.id && price == ad.price && Objects.equals(description, ad.description) && Objects.equals(title, ad.title) && Objects.equals(image, ad.image) && Objects.equals(author, ad.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, title, image, author);
    }
}
