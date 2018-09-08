package hr.ferit.coolschool.controller;

import hr.ferit.coolschool.exception.ResourceNotFoundException;
import hr.ferit.coolschool.model.Quiz;
import hr.ferit.coolschool.model.SchoolType;
import hr.ferit.coolschool.model.Subject;
import hr.ferit.coolschool.model.filter.QuizFilter;
import hr.ferit.coolschool.repository.QuizRepository;
import hr.ferit.coolschool.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping
    public ResponseEntity<?> listQuizes(
            @RequestParam(value = "difficulty", required = false) Integer difficulty,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(value = "classNum", required = false) Integer classNum,
            @RequestParam(value = "schoolType", required = false) SchoolType schoolType,
            @RequestParam(value = "subject", required = false) Subject subject
    ) {
        if (Stream.of(difficulty, enabled, classNum, schoolType, subject).allMatch(Objects::isNull)) {
            return ResponseEntity.ok(this.quizRepository.findAll());
        }
        QuizFilter quizFilter = new QuizFilter().byClassNum(classNum).byDifficulty(difficulty)
                .byEnabled(enabled).bySchoolType(schoolType).bySubject(subject);

        return ResponseEntity.ok(this.quizService.findAllByFilter(quizFilter));
    }

    @PostMapping
    public ResponseEntity<?> insertQuiz(
            @RequestBody @Valid Quiz quiz
    ) {
        return ResponseEntity.ok(this.quizService.save(quiz));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> quizById(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(this.quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ne postoji kviz koji ima id: " + id)));
    }
}
