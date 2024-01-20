package sky.pro.demo_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.demo_shop.entity.Ad;
import sky.pro.demo_shop.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {
    List<Ad> findByAuthor(User user);

    Optional<Ad> findByPk(Integer pk);
}
