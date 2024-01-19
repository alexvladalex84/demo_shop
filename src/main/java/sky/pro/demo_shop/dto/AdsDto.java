package sky.pro.demo_shop.dto;


import java.util.List;


public class AdsDto {
    private int count;
    private List<AdDto> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AdDto> getResults() {
        return results;
    }

    public void setResults(List<AdDto> results) {
        this.results = results;
    }
}
