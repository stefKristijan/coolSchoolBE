package hr.ferit.coolschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_school", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "school_id"})
})
public class UserSchool implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userSchoolId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private Integer classNum;
    private Subject subject;

    public UserSchool(User user, School school, Integer classNum) {
        this.user = user;
        this.school = school;
        this.classNum = classNum;
    }

    public UserSchool(User user, School school, Integer classNum, Subject subject) {
        this.user = user;
        this.school = school;
        this.classNum = classNum;
        this.subject = subject;
    }

    public UserSchool() {
    }

    @Override
    public String toString() {
        return "UserSchool{" +
                "userSchoolId=" + userSchoolId +
                ", user=" + user +
                ", school=" + school +
                ", classNum=" + classNum +
                ", subject=" + subject +
                '}';
    }

    public Long getUserSchoolId() {
        return userSchoolId;
    }

    public void setUserSchoolId(Long userSchoolId) {
        this.userSchoolId = userSchoolId;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
