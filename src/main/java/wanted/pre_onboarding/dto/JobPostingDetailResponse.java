package wanted.pre_onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingDetailResponse {

    private Integer jobPostingId;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int reward;
    private String skill;
    private String content;
    private List<Integer> otherJobPosting;
}
