package wanted.pre_onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "jobposting_id")
    private int id;

    private String position;

    private int reward;

    private String content;

    private String skill;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String nation;

    private String region;

    @Builder
    public JobPosting(String position, int reward, String content, String skill, Company company, String nation, String region) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.skill = skill;
        this.company = company;
        this.nation = nation;
        this.region = region;
    }

    public JobPosting updateJobPosting(String position, Integer reward, String content, String skill, Company company, String nation, String region) {
        if (position != null) this.position = position;
        if (reward != null && reward != 0) this.reward = reward;
        if (content != null) this.content = content;
        if (skill != null) this.skill = skill;
        if (company != null) this.company = company;
        if (nation != null) this.nation = nation;
        if (region != null) this.region = region;
        return this;
    }
}
