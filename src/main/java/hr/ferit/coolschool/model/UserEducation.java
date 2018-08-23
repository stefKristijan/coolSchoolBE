package hr.ferit.coolschool.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_education")
public class UserEducation implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "education_id")
    private EducationProgram educationProgram;

    private Integer classNum;

    public UserEducation(User user, EducationProgram educationProgram, Integer classNum) {
        this.user = user;
        this.educationProgram = educationProgram;
        this.classNum = classNum;
    }

    public UserEducation() {
    }

    @Override
    public String toString() {
        return "UserEducation{" +
                "user=" + user +
                ", educationProgram=" + educationProgram +
                ", classNum=" + classNum +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EducationProgram getEducationProgram() {
        return educationProgram;
    }

    public void setEducationProgram(EducationProgram educationProgram) {
        this.educationProgram = educationProgram;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }
}
