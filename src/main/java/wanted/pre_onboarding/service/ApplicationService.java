package wanted.pre_onboarding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.pre_onboarding.entity.Application;
import wanted.pre_onboarding.entity.JobPosting;
import wanted.pre_onboarding.entity.User;
import wanted.pre_onboarding.repository.ApplicationRepository;
import wanted.pre_onboarding.repository.JobPostingRepository;
import wanted.pre_onboarding.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobPostingRepository jobPostingRepository;

    public void createApplication(Integer userId, Integer jobPostingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));

        applicationRepository.findByUserIdAndJobPostingId(userId, jobPostingId)
                .ifPresent(application -> {
                    throw new IllegalStateException("이미 지원한 공고입니다.");
                });

        applicationRepository.save(Application.builder()
                .user(user)
                .jobPosting(jobPosting)
                .build());
    }
}
