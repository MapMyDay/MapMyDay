package com.mmd_cnu.MapMyDay.model.request;
import lombok.Getter;
import com.mmd_cnu.MapMyDay.entity.Project;
import java.time.LocalDateTime;

@Getter
public class ProjectRequest {
    private String title;

    private String summary;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isInProgress;

    public Project toEntity() {
        return Project.builder()
                .title(title)
                .summary(summary)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .isInProgress(isInProgress)
                .build();
    }
}
