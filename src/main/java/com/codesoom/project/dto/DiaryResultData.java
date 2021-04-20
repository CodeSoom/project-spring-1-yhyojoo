package com.codesoom.project.dto;

import com.codesoom.project.domain.Diary;
import com.codesoom.project.domain.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 다이어리 정보 응답 DTO.
 */
@Getter
@NoArgsConstructor
@ToString
public class DiaryResultData {
    private Long id;

    private String title;

    private String comment;

    private List<Task> tasks = new ArrayList<>();

    @Builder
    public DiaryResultData(
            Long id, String title, String comment, List<Task> tasks
    ) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.tasks = tasks;
    }

    @Builder
    public DiaryResultData(
            Long id, String title, String comment
    ) {
        this.id = id;
        this.title = title;
        this.comment = comment;
    }

    public static DiaryResultData of(Diary diary) {
        return DiaryResultData.builder()
                .id(diary.getId())
                .title(diary.getTitle())
                .comment(diary.getComment())
                .tasks(diary.getTasks())
                .build();
    }
}
