package hr.ferit.coolschool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class School {

    @Id
    @GeneratedValue
    private Integer schoolId;
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer postalCode;
    private SchoolType type;
    private EducationProgram educationProgram;
}
