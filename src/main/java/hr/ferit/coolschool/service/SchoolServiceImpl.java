package hr.ferit.coolschool.service;

import hr.ferit.coolschool.exception.ResourceNotFoundException;
import hr.ferit.coolschool.model.School;
import hr.ferit.coolschool.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public School update(Integer id, School school) {
        this.schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tražena škola ne postoji"));

        school.setSchoolId(id);

        return this.schoolRepository.save(school);
    }
}
