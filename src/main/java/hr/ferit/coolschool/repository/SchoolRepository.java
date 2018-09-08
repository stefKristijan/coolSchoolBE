package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
