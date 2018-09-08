package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.Role;
import hr.ferit.coolschool.model.UserSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSchoolRepository extends JpaRepository<UserSchool, Long> {

    List<UserSchool> findAllBySchoolSchoolIdAndUserRole(Integer schoolId, Role role);

    List<UserSchool> findAllBySchoolSchoolId(Integer schoolId);

    @Modifying
    @Query("DELETE FROM UserSchool WHERE user_id = :userId")
    void deleteAllByUserUserId(@Param("userId") Long userId);
}
