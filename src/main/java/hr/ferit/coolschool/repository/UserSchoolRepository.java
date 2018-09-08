package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.Role;
import hr.ferit.coolschool.model.UserSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSchoolRepository extends JpaRepository<UserSchool, Long> {

    List<UserSchool> findAllBySchoolSchoolIdAndUserRole(Integer schoolId, Role role);

    List<UserSchool> findAllBySchoolSchoolId(Integer schoolId);

    void deleteAllByUserUserId(Long userId);
}
