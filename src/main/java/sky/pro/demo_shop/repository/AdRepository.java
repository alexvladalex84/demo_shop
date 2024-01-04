package sky.pro.demo_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.demo_shop.entity.Ad;

import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad,Integer> {

}
