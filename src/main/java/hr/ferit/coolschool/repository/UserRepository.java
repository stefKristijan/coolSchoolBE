package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User set password = :password where user_id = :id")
    int updatePassword(@Param("id") Long id, @Param("password") String newEncodedPassword);
}
