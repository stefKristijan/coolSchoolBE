package hr.ferit.coolschool.repository;

import hr.ferit.coolschool.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RankingRepository extends JpaRepository<Rank, Long> {

    @Query(nativeQuery = true, name = "findRankings")
    List<Rank> findRankings(
            @Param("city") String city,
            @Param("quizId") Long quizId,
            @Param("schoolId") Integer schoolId,
            @Param("state") String state,
            @Param("subject") Integer subject,
            @Param("schoolType") Integer schoolType
    );
}
