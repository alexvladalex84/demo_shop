package sky.pro.demo_shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.demo_shop.entity.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findUserByUsername(String userName);

//    Optional<Users> findUserByPassword(String password);
}
