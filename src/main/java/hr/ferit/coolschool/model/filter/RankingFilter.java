package hr.ferit.coolschool.model.filter;

import hr.ferit.coolschool.model.Subject;

import java.util.Objects;

public class RankingFilter {

    private Long quizId;
    private Subject subject;
    private Integer classNum;
    private Integer schoolId;
    private String city;
    private String state;

    public RankingFilter byQuiz(Long quizId) {
        if (!Objects.isNull(quizId))
            this.quizId = quizId;
        return this;
    }

    public RankingFilter byCity(String city) {
        if (!Objects.isNull(city))
            this.city = city;
        return this;
    }

    public RankingFilter bySchool(Integer schoolId) {
        if (!Objects.isNull(schoolId))
            this.schoolId = schoolId;
        return this;
    }

    public RankingFilter byClassNum(Integer classNum) {
        if (!Objects.isNull(classNum))
            this.classNum = classNum;
        return this;
    }

    public RankingFilter bySubject(Subject subject) {
        if (!Objects.isNull(subject))
            this.subject = subject;
        return this;
    }

    public RankingFilter byState(String state) {
        if (!Objects.isNull(quizId) && !state.isEmpty())
            this.state = state;
        return this;
    }

    public Long getQuizId() {
        return quizId;
    }

    public Subject getSubject() {
        return subject;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
