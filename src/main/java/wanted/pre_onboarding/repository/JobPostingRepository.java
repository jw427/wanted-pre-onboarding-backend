package wanted.pre_onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wanted.pre_onboarding.entity.JobPosting;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
    Optional<JobPosting> findJobPostingById(Integer id);

    @Query("SELECT j FROM JobPosting j " +
            "WHERE j.skill LIKE :keyword OR j.company.name LIKE :keyword OR j.nation LIKE :keyword " +
            "OR j.region LIKE :keyword OR j.position LIKE :keyword OR CAST(j.reward AS string) LIKE :keyword")
    List<JobPosting> findJobPostingsByKeyword(@Param("keyword") String keyword);

    @Query("SELECT j.id FROM JobPosting j " +
            "WHERE j.company.id = :companyId AND j.id <> :jobPostingId")
    List<Integer> findOtherJobPostingsByCompanyId(@Param("companyId") Integer companyId, @Param("jobPostingId") Integer jobPostingId);
}
