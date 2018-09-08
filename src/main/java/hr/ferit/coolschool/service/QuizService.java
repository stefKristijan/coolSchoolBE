package hr.ferit.coolschool.service;

import hr.ferit.coolschool.model.Quiz;
import hr.ferit.coolschool.model.filter.QuizFilter;

import java.util.List;

public interface QuizService {

    /**
     * Returns a list of quizzes based on the filter restrictions
     *
     * @param quizFilter - restrictions to be applied in the search
     * @return
     */
    List<Quiz> findAllByFilter(QuizFilter quizFilter);
}
