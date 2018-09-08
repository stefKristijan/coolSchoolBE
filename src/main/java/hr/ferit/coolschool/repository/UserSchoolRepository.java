package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.UserSchool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSchoolRepository extends JpaRepository<UserSchool, Long> {
}
