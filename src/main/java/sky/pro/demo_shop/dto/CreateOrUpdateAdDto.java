package sky.pro.demo_shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class CreateOrUpdateAdDto {
    private String title;
    private int price;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
