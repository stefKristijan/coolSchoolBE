package hr.ferit.coolschool.model;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "findAllRankings",
        classes = {
                @ConstructorResult(
                        targetClass = Rank.class,
                        columns = {
                                @ColumnResult(name = "username", type = String.class),
                                @ColumnResult(name = "points", type = float.class),
                                @ColumnResult(name = "schoolName", type = String.class),
                                @ColumnResult(name = "classNum", type = int.class)
                        })
        })
@NamedNativeQuery(name = "findRankings",
        query = "SELECT u.username username, sum(qp.sum_points) points, us.class_num classNum, s.name schoolName " +
                "FROM users u, quiz_participant qp, user_school us, schools s " +
                "WHERE qp.user_id = u.user_id AND us.user_id = u.user_id AND s.school_id = us.school_id " +
                "GROUP BY u.username",
        resultClass = Rank.class,
        resultSetMapping = "findAllRankings"
)
@Entity
public class Rank {

    @Id
    private Long id;
    private String username;
    private float points;
    private String schoolName;
    private int classNum;

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "username='" + username + '\'' +
                ", points=" + points +
                ", schoolName='" + schoolName + '\'' +
                ", classNum=" + classNum +
                '}';
    }

    public Rank(String username, float points, String schoolName, int classNum) {
        this.username = username;
        this.points = points;
        this.schoolName = schoolName;
        this.classNum = classNum;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }
}
