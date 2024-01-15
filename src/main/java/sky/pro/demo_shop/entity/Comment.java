package sky.pro.demo_shop.entity;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "author_id")
    private Users author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_ad")
    private Ad ad;

    public Comment(Long id, LocalDateTime createdAt, String text, Users author, Ad ad) {
        this.id = id;
        this.createdAt = createdAt;
        this.text = text;
        this.author = author;
        this.ad = ad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", ad=" + ad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(text, comment.text) && Objects.equals(author, comment.author) && Objects.equals(ad, comment.ad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, text, author, ad);
    }
}
