package wanted.pre_onboarding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.pre_onboarding.dto.DeleteJobPostingRequest;
import wanted.pre_onboarding.dto.JobPostingDetailResponse;
import wanted.pre_onboarding.dto.JobPostingRequest;
import wanted.pre_onboarding.dto.JobPostingResponse;
import wanted.pre_onboarding.entity.Company;
import wanted.pre_onboarding.entity.JobPosting;
import wanted.pre_onboarding.repository.CompanyRepository;
import wanted.pre_onboarding.repository.JobPostingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    public void createJobPosting(JobPostingRequest request, Integer companyId) {
        Company company = companyRepository.findCompanyById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        jobPostingRepository.save(JobPosting.builder()
                .position(request.getPosition())
                .reward(request.getReward())
                .content(request.getContent())
                .skill(request.getSkill())
                .company(company)
                .nation(request.getNation())
                .region(request.getRegion())
                .build());
    }

    public void updateJobPosting(JobPostingRequest request, Integer companyId, Integer jobPostingId) {
        Company company = companyRepository.findCompanyById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        JobPosting jobPosting = jobPostingRepository.findJobPostingById(jobPostingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공고입니다."));

        jobPosting.updateJobPosting(request.getPosition(), request.getReward(), request.getContent(), request.getSkill(), company, request.getNation(), request.getRegion());
    }

    public void deleteJobPosting(DeleteJobPostingRequest request) {
        JobPosting jobPosting = jobPostingRepository.findJobPostingById(request.getJobPostingId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공고입니다."));

        jobPostingRepository.delete(jobPosting);
    }

    public List<JobPostingResponse> getJobPosting() {
        List<JobPosting> jobPostingList = jobPostingRepository.findAll();
        return transformToJobPostingResponseList(jobPostingList);
    }

    public JobPostingDetailResponse getJobPostingDetail(Integer jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findJobPostingById(jobPostingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공고입니다."));
        List<Integer> otherJobPosting = jobPostingRepository.findOtherJobPostingsByCompanyId(jobPosting.getCompany().getId(), jobPostingId);

        JobPostingDetailResponse jobPostingDetailResponse = JobPostingDetailResponse.builder()
                .jobPostingId(jobPosting.getId())
                .companyName(jobPosting.getCompany().getName())
                .nation(jobPosting.getNation())
                .region(jobPosting.getRegion())
                .position(jobPosting.getPosition())
                .reward(jobPosting.getReward())
                .skill(jobPosting.getSkill())
                .content(jobPosting.getContent())
                .otherJobPosting(otherJobPosting)
                .build();
        return jobPostingDetailResponse;
    }

    public List<JobPostingResponse> searchJobPosting(String keyword) {
        String searchKeyword = "%" + keyword + "%";
        List<JobPosting> jobPostingList = jobPostingRepository.findJobPostingsByKeyword(searchKeyword);
        return transformToJobPostingResponseList(jobPostingList);
    }

    private List<JobPostingResponse> transformToJobPostingResponseList(List<JobPosting> jobPostingList) {
        List<JobPostingResponse> jobPostingResponseList = new ArrayList<>();
        for(int i=0; i<jobPostingList.size(); i++) {
            JobPosting jobPosting = jobPostingList.get(i);
            JobPostingResponse jobPostingResponse = JobPostingResponse.builder()
                    .jobPostingId(jobPosting.getId())
                    .companyName(jobPosting.getCompany().getName())
                    .nation(jobPosting.getNation())
                    .region(jobPosting.getRegion())
                    .position(jobPosting.getPosition())
                    .reward(jobPosting.getReward())
                    .skill(jobPosting.getSkill())
                    .build();
            jobPostingResponseList.add(jobPostingResponse);
        }
        return jobPostingResponseList;
    }
}
