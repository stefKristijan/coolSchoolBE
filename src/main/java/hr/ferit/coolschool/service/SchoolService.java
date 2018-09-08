package hr.ferit.coolschool.service;

import hr.ferit.coolschool.model.School;
import hr.ferit.coolschool.model.User;

import java.util.List;

public interface SchoolService {

    School update(Integer id, School school);

    /**
     * Returns a list of users that are students or teachers in school with the given id
     *
     * @param schoolId - id of the school
     * @param teachersOnly - returns a list of teachers only
     * @param studentsOnly - returns a list of students only
     * @return
     */
    List<User> findAllUsersInSchool(Integer schoolId, boolean teachersOnly, boolean studentsOnly);
}
