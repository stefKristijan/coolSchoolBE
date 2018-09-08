package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
