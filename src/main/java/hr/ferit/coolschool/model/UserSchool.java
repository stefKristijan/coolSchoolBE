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
                "userSchoolId=" + userSchoolId +
                ", user=" + user +
                ", school=" + school +
                ", classNum=" + classNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSchool that = (UserSchool) o;

        if (userSchoolId != null ? !userSchoolId.equals(that.userSchoolId) : that.userSchoolId != null) return false;
        if (!user.equals(that.user)) return false;
        if (!school.equals(that.school)) return false;
        return classNum != null ? classNum.equals(that.classNum) : that.classNum == null;
    }

    @Override
    public int hashCode() {
        int result = userSchoolId != null ? userSchoolId.hashCode() : 0;
        result = 31 * result + user.hashCode();
        result = 31 * result + school.hashCode();
        result = 31 * result + (classNum != null ? classNum.hashCode() : 0);
        return result;
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
}
