package wanted.pre_onboarding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.pre_onboarding.dto.ApplicationRequest;
import wanted.pre_onboarding.service.ApplicationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("")
    public void createApplication(@RequestBody ApplicationRequest request) {
        applicationService.createApplication(request.getUserId(), request.getJobPostingId());
    }
}
