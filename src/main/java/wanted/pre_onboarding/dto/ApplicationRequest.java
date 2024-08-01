package wanted.pre_onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    private Integer userId;
    private Integer jobPostingId;
}
