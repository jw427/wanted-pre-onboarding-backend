package wanted.pre_onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.pre_onboarding.entity.Application;
import wanted.pre_onboarding.entity.JobPosting;
import wanted.pre_onboarding.entity.User;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    Optional<Application> findByUserIdAndJobPostingId(Integer userId, Integer jobPostingId);
}
