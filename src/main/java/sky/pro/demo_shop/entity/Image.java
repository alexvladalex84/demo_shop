package sky.pro.demo_shop.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Transactional
public class Image {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String originalFileName;
    private String filePath;
    private Long fileSize;
    private String mediaType;
    private boolean isPreviewImage;
    @OneToOne
    private Users users;
    @Lob
    private byte[] bytes;
    @ManyToOne
    private Ad ad;

    public Image() {

    }

    public Image(Long id, String name, String originalFileName, String filePath, Long fileSize
            , String mediaType, boolean isPreviewImage, Users users, byte[] bytes, Ad ad) {
        this.id = id;
        this.name = name;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.isPreviewImage = isPreviewImage;
        this.users = users;
        this.bytes = bytes;
        this.ad = ad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public boolean isPreviewImage() {
        return isPreviewImage;
    }

    public void setPreviewImage(boolean previewImage) {
        isPreviewImage = previewImage;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalFileName='" + originalFileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", isPreviewImage=" + isPreviewImage +
                ", users=" + users +
                ", bytes=" + Arrays.toString(bytes) +
                ", ad=" + ad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return isPreviewImage == image.isPreviewImage && Objects.equals(id, image.id) && Objects.equals(name, image.name) && Objects.equals(originalFileName, image.originalFileName) && Objects.equals(filePath, image.filePath) && Objects.equals(fileSize, image.fileSize) && Objects.equals(mediaType, image.mediaType) && Objects.equals(users, image.users) && Arrays.equals(bytes, image.bytes) && Objects.equals(ad, image.ad);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, originalFileName, filePath, fileSize, mediaType, isPreviewImage, users, ad);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }
}
