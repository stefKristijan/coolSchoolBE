package hr.ferit.coolschool.controller;

import hr.ferit.coolschool.model.filter.QuizFilter;
import hr.ferit.coolschool.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseEntity<?> listQuizes(
            @RequestBody(required = false) QuizFilter quizFilter
            ) {
        return ResponseEntity.ok(this.quizService.findAllByFilter(quizFilter));
    }
}
