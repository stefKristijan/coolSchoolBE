package hr.ferit.coolschool.service;

import hr.ferit.coolschool.model.QuizReport;
import hr.ferit.coolschool.model.QuizSolution;

import java.util.List;

public interface QuizSolutionService {

    /**
     * Checks the answers to the given questions from quiz with quizId, grades the solution and
     * returns quiz report
     *
     * @param quizId        - quiz who's answers have been submitted
     * @param quizSolutions - list of questions - given answer to each question
     * @return report of the quiz
     */
    QuizReport submitQuiz(Long quizId, List<QuizSolution> quizSolutions);
}
