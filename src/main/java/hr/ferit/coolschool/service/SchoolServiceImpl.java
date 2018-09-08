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
        School school = this.schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("Tražena škola ne postoji"));

        if (teachersOnly && studentsOnly || !teachersOnly && !studentsOnly) {
            return this.userSchoolRepository.findAllBySchoolSchoolId(schoolId).stream()
                    .map(UserSchool::getUser).collect(Collectors.toList());
        }else if(studentsOnly){
            System.out.println("Students only");
            return this.userSchoolRepository.findAllBySchoolSchoolIdAndUserRole(schoolId, Role.ROLE_STUDENT).stream()
                    .map(UserSchool::getUser).collect(Collectors.toList());
        }else{
            System.out.println("Teachers only");
            return this.userSchoolRepository.findAllBySchoolSchoolIdAndUserRole(schoolId, Role.ROLE_TEACHER).stream()
                    .map(UserSchool::getUser).collect(Collectors.toList());
        }
    }
}
