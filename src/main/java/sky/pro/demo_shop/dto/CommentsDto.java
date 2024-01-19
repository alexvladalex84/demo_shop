package sky.pro.demo_shop.dto;


import java.util.List;


public class CommentsDto {
    private int count;
    private List<CommentDto> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CommentDto> getResults() {
        return results;
    }

    public void setResults(List<CommentDto> results) {
        this.results = results;
    }
}
