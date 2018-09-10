package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Modifying
    @Query("DELETE FROM Answer WHERE question_id = :questionId")
    void deleteAllByQuestionId(@Param("questionId") Long questionId);
}
