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

    /**
     * Inserts the quiz in the database with all the questions and answers, checks if summary of
     * correct answers is equal to maximum points field in the quiz
     *
     * @param quiz - a quiz with questions and answers to them
     * @return the saved Quiz with all id's
     */
    Quiz save(Quiz quiz);
}
