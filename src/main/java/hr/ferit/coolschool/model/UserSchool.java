package hr.ferit.coolschool.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_school")
public class UserSchool implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private Integer classNum;

    public UserSchool(User user, School school, Integer classNum) {
        this.user = user;
        this.school = school;
        this.classNum = classNum;
    }

    public UserSchool() {
    }

    @Override
    public String toString() {
        return "UserSchool{" +
                "user=" + user +
                ", school=" + school +
                ", classNum=" + classNum +
                '}';
    }

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
}
