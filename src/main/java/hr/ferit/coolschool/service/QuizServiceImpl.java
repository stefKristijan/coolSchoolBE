package hr.ferit.coolschool.service;

import hr.ferit.coolschool.exception.QuizException;
import hr.ferit.coolschool.model.Answer;
import hr.ferit.coolschool.model.Question;
import hr.ferit.coolschool.model.Quiz;
import hr.ferit.coolschool.model.filter.QuizFilter;
import hr.ferit.coolschool.repository.AnswerRepository;
import hr.ferit.coolschool.repository.QuestionRepository;
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
import java.util.Set;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Quiz> findAllByFilter(QuizFilter quizFilter) {
        if (Objects.isNull(quizFilter)) {
            System.out.println("FindAll()");
            return this.quizRepository.findAll();
        }
        List<QuizSpecification> specifications = new ArrayList<>();
        if (!Objects.isNull(quizFilter.getEnabled())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("enabled", ":", quizFilter.getEnabled())
            ));
            System.out.println("Added enabled spec" + specifications);
        }
        if (!Objects.isNull(quizFilter.getClassNum())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("classNum", ":", quizFilter.getClassNum())
            ));
        }
        if (!Objects.isNull(quizFilter.getDifficulty())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("difficulty", ":", quizFilter.getDifficulty())
            ));
            System.out.println("Added difficulty spec" + specifications);
        }
        if (!Objects.isNull(quizFilter.getSchoolType())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("schoolType", ":", quizFilter.getSchoolType())
            ));
        }
        if (!Objects.isNull(quizFilter.getSubject())) {
            specifications.add(new QuizSpecification(
                    new SearchCriteria("subject", ":", quizFilter.getSubject())
            ));
        }
        return this.quizRepository.findAll(appendSpecsFromList(specifications));
    }

    @Override
    public Quiz save(Quiz quiz) {
        if (checkPointsAndAnswers(quiz.getQuestions()) != quiz.getMaxPoints()) {
            throw new QuizException("Maksimalan broj bodova i suma bodova po odgovoru se ne poklapaju");
        }

        Quiz savedQuiz = this.quizRepository.save(quiz);
        quiz.getQuestions().forEach(q -> {
            q.setQuiz(savedQuiz);
            q.getAnswers().forEach( a -> {
                a.setQuestion(q);
                answerRepository.save(a);
            });
        });

        return this.quizRepository.findById(savedQuiz.getQuizId())
                .orElseThrow(() -> new RuntimeException("Nešto je pošlo po zlu"));
    }

    private float checkPointsAndAnswers(Set<Question> questions) {
        float sumOfPoints = 0;
        for (Question question : questions) {
            int correctAnswers = 0;
            for (Answer answer : question.getAnswers()) {
                if (answer.isCorrect()) correctAnswers++;
                sumOfPoints += answer.getPoints();
            }
            if (correctAnswers > 1) {
                throw new QuizException("U pitanju: \"" + question.getQuestionText() + "\" postoji previše " +
                        "točnih odgovora, svako pitanje može imati samo jedan točan odgovor");
            }
            if (correctAnswers < 1) {
                throw new QuizException("Za pitanje: \"" + question.getQuestionText() + "\" ne postoji točan odgovor");
            }
        }
        return sumOfPoints;
    }

    private Specification<Quiz> appendSpecsFromList(List<QuizSpecification> specifications) {
        Specification<Quiz> spec = Specification.where(specifications.get(0));
        if (specifications.size() > 1)
            for (int i = 1; i < specifications.size(); i++) {
                spec = spec.and(specifications.get(i));
            }
        return spec;
    }
}
