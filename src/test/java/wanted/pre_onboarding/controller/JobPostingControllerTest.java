package wanted.pre_onboarding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import wanted.pre_onboarding.entity.Company;
import wanted.pre_onboarding.entity.JobPosting;
import wanted.pre_onboarding.service.JobPostingService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class JobPostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JobPostingService jobPostingService;

    @DisplayName("채용공고 등록 테스트")
    @Test
    public void createJobPosting() throws Exception {
        Company company = new Company(1, "아무개회사");

        JobPosting testJobPosting = JobPosting.builder()
                .position("백엔드 개발자")
                .reward(1000000)
                .content("백엔드 개발자를 채용합니다.")
                .skill("Java")
                .company(company)
                .nation("한국")
                .region("서울")
                .build();
        String json = objectMapper.writeValueAsString(testJobPosting);

        mockMvc.perform(post("/jobposting/{companyId}", company.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
