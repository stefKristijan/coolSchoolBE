package hr.ferit.coolschool.model.filter;

import hr.ferit.coolschool.model.SchoolType;
import hr.ferit.coolschool.model.Subject;

public class QuizFilter {
    private Integer classNum;
    private SchoolType schoolType;
    private Subject subject;
    private Integer difficulty;
    private Boolean enabled;

    @Override
    public String toString() {
        return "QuizFilter{" +
                "classNum=" + classNum +
                ", schoolType=" + schoolType +
                ", subject=" + subject +
                ", difficulty=" + difficulty +
                ", enabled=" + enabled +
                '}';
    }

    public QuizFilter byEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public QuizFilter byClassNum(Integer classNum) {
        this.classNum = classNum;
        return this;
    }

    public QuizFilter bySubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public QuizFilter bySchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
        return this;
    }

    public QuizFilter byDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public SchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
