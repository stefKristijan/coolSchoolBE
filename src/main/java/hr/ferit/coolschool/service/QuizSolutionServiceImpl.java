package hr.ferit.coolschool.service;

import hr.ferit.coolschool.exception.ResourceNotFoundException;
import hr.ferit.coolschool.model.*;
import hr.ferit.coolschool.repository.QuizParticipantRepository;
import hr.ferit.coolschool.repository.QuizRepository;
import hr.ferit.coolschool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizSolutionServiceImpl implements QuizSolutionService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizParticipantRepository quizParticipantRepository;

    @Override
    public QuizReport submitQuiz(Long quizId, List<QuizSolution> quizSolutions) {
        Quiz quiz = this.quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Kviz za koji ste predali odgovore ne postoji"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnički račun ne postoji"));

        int fullTime = quizSolutions.stream().mapToInt(QuizSolution::getTime).sum();
        float pointsSum = calculatePoints(quizSolutions, quiz) * fullTime / 1000;
        QuizParticipant quizResults = new QuizParticipant(pointsSum, fullTime, user, quiz);
        quizSolutions.forEach(qs -> qs.getGivenAnswer().setQuestion(qs.getQuestion()));
        Set<Answer> givenAnswers = quizSolutions.stream().map(QuizSolution::getGivenAnswer).collect(Collectors.toSet());
        quizResults.setParticipantAnswers(givenAnswers);

        quizParticipantRepository.save(quizResults);

        return new QuizReport(quizSolutions, pointsSum, fullTime);
    }

    private float calculatePoints(List<QuizSolution> quizSolutions, Quiz quiz) {
        float points = 0;
        for (QuizSolution quizSolution : quizSolutions) {
            Question question = quiz.getQuestions().stream()
                    .filter(q -> q.getQuestionId().equals(quizSolution.getQuestion().getQuestionId())).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format("Pitanje \"%s\" nije pronađeno", quizSolution.getQuestion().getQuestionText())
                    ));
            Answer correct = question.getAnswers().stream().filter(Answer::isCorrect).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format("Ne može se pronaći točan odgovor na pitanje \"%s\"", question.getQuestionText())
                    ));
            quizSolution.getGivenAnswer().setQuestion(question);
            quizSolution.checkAnswer(correct);
            points += quizSolution.getPoints();
        }
        return points;
    }
}
