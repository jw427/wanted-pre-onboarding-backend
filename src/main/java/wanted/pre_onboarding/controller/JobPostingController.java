package wanted.pre_onboarding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.pre_onboarding.dto.DeleteJobPostingRequest;
import wanted.pre_onboarding.dto.JobPostingDetailResponse;
import wanted.pre_onboarding.dto.JobPostingRequest;
import wanted.pre_onboarding.dto.JobPostingResponse;
import wanted.pre_onboarding.service.JobPostingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobposting")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    /**
     * 채용공고 등록
     */
    @PostMapping("/{companyId}")
    public void createJobPosting(@RequestBody JobPostingRequest request, @PathVariable Integer companyId) {
        jobPostingService.createJobPosting(request, companyId);
    }

    /**
     * 채용공고 수정
     */
    @PutMapping("/{companyId}/{jobPostingId}")
    public void updateJobPosting(@RequestBody JobPostingRequest request, @PathVariable Integer companyId, @PathVariable Integer jobPostingId) {
        jobPostingService.updateJobPosting(request, companyId, jobPostingId);
    }

    /**
     * 채용공고 삭제
     */
    @DeleteMapping("")
    public void deleteJobPosting(@RequestBody DeleteJobPostingRequest request) {
        jobPostingService.deleteJobPosting(request);
    }

    /**
     * 채용공고 전체 조회
     */
    @GetMapping("")
    public ResponseEntity<List<JobPostingResponse>> getJobPosting() {
        List<JobPostingResponse> jobPostingResponseList = jobPostingService.getJobPosting();
        return new ResponseEntity<>(jobPostingResponseList, HttpStatus.OK);
    }

    /**
     * 채용공고 상세 페이지
     */
    @GetMapping("/{jobPostingId}")
    public ResponseEntity<JobPostingDetailResponse> getJobPostingDetail(@PathVariable Integer jobPostingId) {
        JobPostingDetailResponse jobPostingDetailResponse = jobPostingService.getJobPostingDetail(jobPostingId);
        return new ResponseEntity<>(jobPostingDetailResponse, HttpStatus.OK);
    }

    /**
     * 채용공고 검색
     */
    @GetMapping("/search")
    public ResponseEntity<List<JobPostingResponse>> searchJobPosting(@RequestParam String keyword) {
        List<JobPostingResponse> jobPostingResponseList = jobPostingService.searchJobPosting(keyword);
        return new ResponseEntity<>(jobPostingResponseList, HttpStatus.OK);
    }
}
