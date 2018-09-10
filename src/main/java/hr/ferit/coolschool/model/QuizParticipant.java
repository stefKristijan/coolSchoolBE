package hr.ferit.coolschool.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "quiz_participant", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"quiz_id", "user_id"})
})
public class QuizParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    private Float sumPoints;
    //time in seconds
    private Integer time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "participant_answers",
            joinColumns = {@JoinColumn(name = "participant_id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id")}
    )
    private Set<Answer> participantAnswers;

    public QuizParticipant(Float sumPoints, Integer time, User user, Quiz quiz) {
        this.sumPoints = sumPoints;
        this.time = time;
        this.user = user;
        this.quiz = quiz;
    }

    public QuizParticipant() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizParticipant that = (QuizParticipant) o;

        if (participantId != null ? !participantId.equals(that.participantId) : that.participantId != null)
            return false;
        if (sumPoints != null ? !sumPoints.equals(that.sumPoints) : that.sumPoints != null) return false;
        return time != null ? time.equals(that.time) : that.time == null;
    }

    @Override
    public int hashCode() {
        int result = participantId != null ? participantId.hashCode() : 0;
        result = 31 * result + (sumPoints != null ? sumPoints.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuizParticipant{" +
                "participantId=" + participantId +
                ", sumPoints=" + sumPoints +
                ", time=" + time +
                ", user=" + user +
                ", quiz=" + quiz +
                ", participantAnswers=" + participantAnswers +
                '}';
    }

    public Set<Answer> getParticipantAnswers() {
        return participantAnswers;
    }

    public void setParticipantAnswers(Set<Answer> participantAnswers) {
        this.participantAnswers = participantAnswers;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Float getSumPoints() {
        return sumPoints;
    }

    public void setSumPoints(Float sumPoints) {
        this.sumPoints = sumPoints;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
