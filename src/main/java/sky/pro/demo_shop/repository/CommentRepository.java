package sky.pro.demo_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.demo_shop.entity.Ad;
import sky.pro.demo_shop.entity.Comment;
import sky.pro.demo_shop.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByAdAuthor(User user);

    List<Comment> findByAd(Ad ad);

    Optional<Comment> findByPk(Integer pk);
}
