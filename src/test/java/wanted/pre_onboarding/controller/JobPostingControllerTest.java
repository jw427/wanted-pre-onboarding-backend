package wanted.pre_onboarding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;
import wanted.pre_onboarding.dto.DeleteJobPostingRequest;
import wanted.pre_onboarding.entity.Company;
import wanted.pre_onboarding.entity.JobPosting;
import wanted.pre_onboarding.service.JobPostingService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private JobPostingController jobPostingController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobPostingController)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

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

    @DisplayName("채용공고 수정 테스트")
    @Test
    public void updateJobPosting() throws Exception {
        JobPosting testJobPosting = JobPosting.builder()
                .position("Javascript 개발자")
                .content("Javascript 개발자를 채용합니다.")
                .skill("Javascript")
                .build();
        String json = objectMapper.writeValueAsString(testJobPosting);

        mockMvc.perform(put("/jobposting/{companyId}/{jobPostingId}", 1, 6)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @DisplayName("채용공고 삭제 테스트")
    @Test
    public void deleteJobPosting() throws Exception {
        DeleteJobPostingRequest deleteJobPostingRequest = DeleteJobPostingRequest.builder()
                .jobPostingId(6)
                .build();
        String json = objectMapper.writeValueAsString(deleteJobPostingRequest);

        mockMvc.perform(delete("/jobposting")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @DisplayName("채용공고 전체 조회 테스트")
    @Test
    public void getJobPosting() throws Exception {
        MvcResult result = mockMvc.perform(get("/jobposting")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        String jobPostingJson = objectMapper.writeValueAsString(jobPostingService.getJobPosting());

        assertEquals(jobPostingJson, response);
    }

    @DisplayName("채용공고 상세 페이지 테스트")
    @Test
    public void getJobPostingDetail() throws Exception {
        MvcResult result = mockMvc.perform(get("/jobposting/{jobPostingId}", 6)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        String jobPostingDetailJson = objectMapper.writeValueAsString(jobPostingService.getJobPostingDetail(6));

        assertEquals(jobPostingDetailJson, response);
    }

    @DisplayName("채용공고 검색 테스트")
    @Test
    public void searchJobPosting() throws Exception {
        MvcResult result = mockMvc.perform(get("/jobposting/search")
                .param("keyword", "프론트엔드")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        String searchJobPostingJson = objectMapper.writeValueAsString(jobPostingService.searchJobPosting("프론트엔드"));

        assertEquals(searchJobPostingJson, response);
    }
}
