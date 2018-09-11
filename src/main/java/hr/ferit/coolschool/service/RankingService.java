package hr.ferit.coolschool.service;

import hr.ferit.coolschool.model.Rank;

import java.util.List;

public interface RankingService {
    List<Rank> findWithoutFilter();
}
