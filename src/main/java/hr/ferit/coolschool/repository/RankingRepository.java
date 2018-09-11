package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankingRepository extends JpaRepository<Rank, Long> {

    @Query(nativeQuery = true, name = "findRankings")
    List<Rank> findRankings();
}
