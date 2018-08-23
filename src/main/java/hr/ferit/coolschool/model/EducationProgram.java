package hr.ferit.coolschool.model;

import javax.persistence.*;

@Entity
@Table(name = "education_program")
public class EducationProgram {

    @Id
    @GeneratedValue
    private Integer educationId;

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "school_education",
            joinColumns = {}
    )
    private School school;

    public EducationProgram(String name, School school) {
        this.name = name;
        this.school = school;
    }

    public EducationProgram() {
    }

    @Override
    public String toString() {
        return "EducationProgram{" +
                "educationId=" + educationId +
                ", name='" + name + '\'' +
                ", school=" + school +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EducationProgram that = (EducationProgram) o;

        if (!educationId.equals(that.educationId)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = educationId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public Integer getEducationId() {
        return educationId;
    }

    public void setEducationId(Integer educationId) {
        this.educationId = educationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
