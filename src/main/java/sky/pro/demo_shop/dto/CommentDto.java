package sky.pro.demo_shop.dto;


public class CommentDto {
    private int author;    // id автора коментария
    private String authorImage;   //ссылка на аватар автора комментария
    private String authorFirstName;  //имя создателя комментария
    private Integer createdAt;        //дата и время создания комментария  00:00:00 01.01.1970
    private int pk;               //id комментария
    private String text;          //текст комментария
}