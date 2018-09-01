package hr.ferit.coolschool.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    private String questionText;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    public Question(String questionText, String imagePath, Quiz quiz) {
        this.questionText = questionText;
        this.imagePath = imagePath;
        this.quiz = quiz;
    }

    public Question() {
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionText='" + questionText + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", quiz=" + quiz +
                ", answers=" + answers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!questionId.equals(question.questionId)) return false;
        if (!questionText.equals(question.questionText)) return false;
        return imagePath != null ? imagePath.equals(question.imagePath) : question.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = questionId.hashCode();
        result = 31 * result + questionText.hashCode();
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    public Set<Answer> getAnswer() {
        return answers;
    }

    public void setAnswer(Set<Answer> answers) {
        this.answers = answers;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}

