package hr.ferit.coolschool.service;

import hr.ferit.coolschool.model.Rank;
import hr.ferit.coolschool.model.filter.RankingFilter;

import java.util.List;

public interface RankingService {
    /**
     * Returns a ranking list of students that corresponds to the filter
     *
     * @param rankingFilter - restrictions in search
     * @return list of users ranked by points
     */
    List<Rank> findByFilter(RankingFilter rankingFilter);
}
