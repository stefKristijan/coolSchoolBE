package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.QuizParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizParticipantRepository extends JpaRepository<QuizParticipant, Long> {
}
