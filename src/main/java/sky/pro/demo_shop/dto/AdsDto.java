package sky.pro.demo_shop.dto;


import java.util.List;
import java.util.Objects;

public class AdsDto {
    private int count;
    private List<AdDto> result;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AdDto> getResult() {
        return result;
    }

    public void setResult(List<AdDto> result) {
        this.result = result;
    }
}