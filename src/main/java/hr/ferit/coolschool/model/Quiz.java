package hr.ferit.coolschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;
    @NotNull
    private String name;
    private String description;
    @CreationTimestamp
    private LocalDateTime creationTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Min(value = 1, message = "Unesite razred/godinu za koji/u je namijenjen kviz (od 1 do 8)")
    @Max(value = 9, message = "Unesite razred/godinu za koji/u je namijenjen kviz (od 1 do 8)")
    private int classNum;
    @NotNull(message = "Odaberite tip škole na koji se ovaj kviz odnosi")
    private SchoolType schoolType;
    @NotNull(message = "Odaberite predmet za koji je ovaj kviz napravljen")
    private Subject subject;
    @NotNull(message = "Unesite maksimalan broj bodova")
    @Min(value = 10, message = "Minimalan broj bodova za kviz je 10")
    @Max(value = 100, message = "Maksimalan broj bodova za kviz je 100")
    private Float maxPoints;
    @Min(value = 0, message = "Odaberite težinu između 0 i 10")
    @Max(value = 10, message = "Odaberite težinu izemđu 0 i 10")
    private int difficulty;
    private boolean enabled = true;

    @OneToMany(mappedBy = "quiz", cascade = {CascadeType.ALL})
    @NotNull(message = "Unesite pitanja koja će se pojaviti u kvizu")
    @NotEmpty(message = "Unesite pitanja koja će se pojaviti u kvizu")
    @Valid
    private Set<Question> questions;

    @OneToMany(mappedBy = "quiz", cascade = {CascadeType.ALL})
    private Set<QuizParticipant> quizParticipants;

    public Quiz(LocalDateTime creationTime, LocalDateTime startTime, LocalDateTime endTime, Integer classNum, SchoolType schoolType, Subject subject, Float maxPoints, int difficulty, boolean enabled) {
        this.creationTime = creationTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classNum = classNum;
        this.schoolType = schoolType;
        this.subject = subject;
        this.maxPoints = maxPoints;
        this.difficulty = difficulty;
        this.enabled = enabled;
    }

    public Quiz() {
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + quizId +
                ", creationTime=" + creationTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", classNum=" + classNum +
                ", schoolType=" + schoolType +
                ", subject=" + subject +
                ", maxPoints=" + maxPoints +
                ", difficulty=" + difficulty +
                ", enabled=" + enabled +
                ", questions=" + questions +
                ", quizParticipants=" + quizParticipants +
                ", name=" + name +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        if (difficulty != quiz.difficulty) return false;
        if (enabled != quiz.enabled) return false;
        if (!quizId.equals(quiz.quizId)) return false;
        if (creationTime != null ? !creationTime.equals(quiz.creationTime) : quiz.creationTime != null) return false;
        if (startTime != null ? !startTime.equals(quiz.startTime) : quiz.startTime != null) return false;
        if (endTime != null ? !endTime.equals(quiz.endTime) : quiz.endTime != null) return false;
        if (classNum != quiz.classNum) return false;
        if (schoolType != quiz.schoolType) return false;
        if (subject != quiz.subject) return false;
        if (name != quiz.name) return false;
        if (description != quiz.description) return false;
        return maxPoints.equals(quiz.maxPoints);
    }

    @Override
    public int hashCode() {
        int result = quizId.hashCode();
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + classNum;
        result = 31 * result + schoolType.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + maxPoints.hashCode();
        result = 31 * result + difficulty;
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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

    public Float getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Float maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @JsonIgnore
    public Set<QuizParticipant> getQuizParticipants() {
        return quizParticipants;
    }

    public void setQuizParticipants(Set<QuizParticipant> quizParticipants) {
        this.quizParticipants = quizParticipants;
    }
}
