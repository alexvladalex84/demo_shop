package sky.pro.demo_shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.demo_shop.entity.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findUserByUsername(String userName);

    Optional<Users> findByEmail(String email);
//    Users findByEmail(String email);

    Optional<Users>findUserByLogin(String username);
//    Optional<Users> findUserByPassword(String password);
}
