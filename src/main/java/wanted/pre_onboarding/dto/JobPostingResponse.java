package wanted.pre_onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingResponse {

    private Integer jobPostingId;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int reward;
    private String skill;
}
