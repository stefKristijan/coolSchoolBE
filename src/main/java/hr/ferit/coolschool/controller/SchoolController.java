package hr.ferit.coolschool.controller;

import hr.ferit.coolschool.exception.ResourceNotFoundException;
import hr.ferit.coolschool.model.School;
import hr.ferit.coolschool.repository.SchoolRepository;
import hr.ferit.coolschool.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping
    public ResponseEntity<List<School>> listSchools() {
        return ResponseEntity.ok(this.schoolRepository.findAll());
    }

    @PostMapping("")
    public ResponseEntity<School> insertSchool(
            @RequestBody @Valid School school
    ) {
        School newSchool = this.schoolRepository.save(school);
        return ResponseEntity.ok(newSchool);
    }

    @GetMapping("{id}")
    public ResponseEntity<School> schoolById(
            @PathVariable Integer id
    ) {
        Optional<School> school = this.schoolRepository.findById(id);
        if (school.isPresent()) {
            return ResponseEntity.ok(school.get());
        }
        throw new ResourceNotFoundException("Tražena škola ne postoji");
    }

    @PutMapping("{id}")
    public ResponseEntity<School> updateSchool(
            @PathVariable Integer id,
            @RequestBody @Valid School school
    ) {
        School updatedSchool = this.schoolService.update(id, school);
        return ResponseEntity.ok(updatedSchool);
    }

    @GetMapping("{id}/users")
    public ResponseEntity<?> getUsersInSchool(
            @PathVariable("id") Integer schoolId,
            @RequestParam(value = "teachers", required = false, defaultValue = "false") boolean teachersOnly,
            @RequestParam(value = "students", required = false, defaultValue = "false") boolean studentsOnly
    ) {
        return ResponseEntity.ok(this.schoolService.findAllUsersInSchool(schoolId, teachersOnly, studentsOnly));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteSchool(
            @PathVariable("id") Integer schoolId
    ) {
        this.schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("Tražena škola ne postoji"));

        this.schoolRepository.deleteById(schoolId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
