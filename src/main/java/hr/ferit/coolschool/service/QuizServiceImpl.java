package hr.ferit.coolschool.service;

import hr.ferit.coolschool.model.Quiz;
import hr.ferit.coolschool.model.filter.QuizFilter;
import hr.ferit.coolschool.repository.QuizRepository;
import hr.ferit.coolschool.utils.QuizSpecification;
import hr.ferit.coolschool.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public List<Quiz> findAllByFilter(QuizFilter quizFilter) {
        if (Objects.isNull(quizFilter)) {
            return this.quizRepository.findAll();
        }
        List<QuizSpecification> specifications = new ArrayList<>();
        if (!Objects.isNull(quizFilter.getEnabled())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("enabled", "=", quizFilter.getEnabled())
            ));
        }
        if (!Objects.isNull(quizFilter.getClassNum())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("classNum", "=", quizFilter.getClassNum())
            ));
        }
        if (!Objects.isNull(quizFilter.getDifficulty())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("difficulty", "=", quizFilter.getDifficulty())
            ));
        }
        if (!Objects.isNull(quizFilter.getSchoolType())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("schoolType", "=", quizFilter.getSchoolType())
            ));
        }
        if (!Objects.isNull(quizFilter.getSubject())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("subject", "=", quizFilter.getSubject())
            ));
        }
        return this.quizRepository.findAll(appendSpecsFromList(specifications));
    }

    private Specification<Quiz> appendSpecsFromList(List<QuizSpecification> specifications) {
        Specification<Quiz> spec = Specification.where(specifications.get(0));
        if (specifications.size() > 1)
            for (int i = 1; i < specifications.size(); i++) {
                spec.and(specifications.get(i));
            }
        return spec;
    }
}
