package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
