package com.codesoom.project.dto;

import com.codesoom.project.domain.Diary;
import com.codesoom.project.domain.Task;
import com.github.dozermapper.core.Mapping;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 다이어리 생성 요청 DTO.
 */
@Getter
@NoArgsConstructor
public class DiaryCreateData {
    @NotBlank(message = "다이어리 제목을 입력해 주세요.")
    @Mapping("title")
    private String title;

    @Mapping("comment")
    private String comment;

    @Mapping("tasks")
    private List<Task> tasks = new ArrayList<>();

    @Builder
    public DiaryCreateData(String title, String comment, List<Task> tasks) {
        this.title = title;
        this.comment = comment;
        this.tasks = tasks;
    }

    @Builder
    public DiaryCreateData(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }

    public Diary toEntity() {
        return Diary.builder()
                .title(this.title)
                .comment(this.comment)
                .build();
    }
}
