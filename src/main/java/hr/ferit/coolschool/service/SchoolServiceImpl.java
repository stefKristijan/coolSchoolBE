package hr.ferit.coolschool.service;

import hr.ferit.coolschool.exception.ResourceNotFoundException;
import hr.ferit.coolschool.model.Role;
import hr.ferit.coolschool.model.School;
import hr.ferit.coolschool.model.User;
import hr.ferit.coolschool.model.UserSchool;
import hr.ferit.coolschool.repository.SchoolRepository;
import hr.ferit.coolschool.repository.UserSchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserSchoolRepository userSchoolRepository;

    @Override
    public School update(Integer id, School school) {
        this.schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tražena škola ne postoji"));

        school.setSchoolId(id);

        return this.schoolRepository.save(school);
    }

    @Override
    public List<User> findAllUsersInSchool(Integer schoolId, boolean teachersOnly, boolean studentsOnly) {
        this.schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("Tražena škola ne postoji"));

        if (teachersOnly && studentsOnly || !teachersOnly && !studentsOnly) {
            return this.userSchoolRepository.findAllBySchoolSchoolId(schoolId).stream()
                    .map(removeSchoolsForMap()).collect(Collectors.toList());
        } else if (studentsOnly) {
            return this.userSchoolRepository.findAllBySchoolSchoolIdAndUserRole(schoolId, Role.ROLE_STUDENT).stream()
                    .map(removeSchoolsForMap()).collect(Collectors.toList());
        } else {
            return this.userSchoolRepository.findAllBySchoolSchoolIdAndUserRole(schoolId, Role.ROLE_TEACHER).stream()
                    .map(removeSchoolsForMap()).collect(Collectors.toList());
        }
    }

    private Function<UserSchool, User> removeSchoolsForMap() {
        return us -> {
            us.getUser().setUserSchools(null);
            return us.getUser();
        };
    }
}
