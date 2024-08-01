package wanted.pre_onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingRequest {

    private String position;
    private int reward;
    private String content;
    private String skill;
    private String nation;
    private String region;
}
