package hr.ferit.coolschool.model.filter;

import hr.ferit.coolschool.model.SchoolType;
import hr.ferit.coolschool.model.Subject;

import java.util.Objects;

public class RankingFilter {

    private Long quizId;
    private Subject subject;
    private SchoolType schoolType;
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

    public RankingFilter bySchoolType(SchoolType schoolType) {
        if (!Objects.isNull(schoolType))
            this.schoolType = schoolType;
        return this;
    }

    public RankingFilter bySubject(Subject subject) {
        if (!Objects.isNull(subject))
            this.subject = subject;
        return this;
    }

    public RankingFilter byState(String state) {
        if (!Objects.isNull(state) && !state.isEmpty())
            this.state = state;
        return this;
    }

    public Long getQuizId() {
        return quizId;
    }

    public Integer getSubject() {
        for(int i=0; i<Subject.values().length; i++){
            if(Subject.values()[i].equals(subject)){
                return i;
            }
        }
        return null;
    }

    public Integer getSchoolType() {
        for(int i=0; i<SchoolType.values().length; i++){
            if(SchoolType.values()[i].equals(schoolType)){
                return i;
            }
        }
        return null;
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
