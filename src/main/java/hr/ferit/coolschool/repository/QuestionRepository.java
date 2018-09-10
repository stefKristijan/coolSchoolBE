package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Modifying
    @Query("DELETE FROM Question WHERE quiz_id = :quizId")
    void deleteAllByQuizId(@Param("quizId") Long quizId);
}
