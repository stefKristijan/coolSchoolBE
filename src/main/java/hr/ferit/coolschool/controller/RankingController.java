package hr.ferit.coolschool.controller;

import hr.ferit.coolschool.model.Subject;
import hr.ferit.coolschool.model.filter.RankingFilter;
import hr.ferit.coolschool.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/rankings")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    //select u.username, sum(qp.sum_points) from users u, quiz_participant qp where qp.user_id = u.user_id GROUP BY u.username
    @GetMapping
    public ResponseEntity<?> studentRanking(
            @RequestParam(value = "quizId", required = false) Long quizId,
            @RequestParam(value = "subject", required = false) Subject subject,
            @RequestParam(value = "classNum", required = false) Integer classNum,
            @RequestParam(value = "schoolId", required = false) Integer schoolId,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state
    ) {
        if (Stream.of(quizId, subject, classNum, schoolId, subject, city, state).allMatch(Objects::isNull)) {
            return ResponseEntity.ok(this.rankingService.findWithoutFilter());
        }
        RankingFilter rankingFilter;
        return null;
    }
}
