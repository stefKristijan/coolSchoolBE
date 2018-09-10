package hr.ferit.coolschool.controller;

import hr.ferit.coolschool.model.QuizReport;
import hr.ferit.coolschool.model.QuizSolution;
import hr.ferit.coolschool.service.QuizSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quiz-results")
public class QuizResultController {

    @Autowired
    private QuizSolutionService quizSolutionService;

    @PostMapping("{quizId}/submit")
    public ResponseEntity<?> submitQuizResult(
            @PathVariable("quizId") Long quizId,
            @RequestBody List<QuizSolution> quizSolutions
    ){
        QuizReport quizReport = this.quizSolutionService.submitQuiz(quizId, quizSolutions);
        return ResponseEntity.ok(quizReport);
    }
}
