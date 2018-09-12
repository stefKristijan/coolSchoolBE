package hr.ferit.coolschool.controller;

import hr.ferit.coolschool.model.SchoolType;
import hr.ferit.coolschool.model.Subject;
import hr.ferit.coolschool.model.filter.RankingFilter;
import hr.ferit.coolschool.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(value = "schoolId", required = false) Integer schoolId,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "schoolType", required = false) SchoolType schoolType
            ) {
        RankingFilter rankingFilter = new RankingFilter()
                .byQuiz(quizId).bySubject(subject).bySchool(schoolId)
                .byCity(city).byState(state).bySchoolType(schoolType);

        return ResponseEntity.ok(this.rankingService.findByFilter(rankingFilter));
    }
}
