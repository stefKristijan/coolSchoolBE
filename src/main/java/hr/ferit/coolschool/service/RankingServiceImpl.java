package hr.ferit.coolschool.service;

import hr.ferit.coolschool.model.Rank;
import hr.ferit.coolschool.model.filter.RankingFilter;
import hr.ferit.coolschool.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RankingServiceImpl implements RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    @Override
    public List<Rank> findByFilter(RankingFilter rankingFilter) {
        return this.rankingRepository.findRankings(
                rankingFilter.getCity(),
                rankingFilter.getQuizId(),
                rankingFilter.getSchoolId(),
                rankingFilter.getState(),
                rankingFilter.getSubject(),
                rankingFilter.getSchoolType()
        );
    }
}
