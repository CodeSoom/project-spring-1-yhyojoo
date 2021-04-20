package com.codesoom.project.dto;

import com.codesoom.project.domain.Diary;
import com.codesoom.project.domain.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 할 일 정보 응답 DTO.
 */
@Getter
@NoArgsConstructor
@ToString
public class TaskResultData {
    private Long id;

    private String title;

    private Diary diary;

    @Builder
    public TaskResultData(Long id, String title, Diary diary) {
        this.id = id;
        this.title = title;
        this.diary = diary;
    }

    @Builder
    public TaskResultData(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static TaskResultData of(Task task) {
        return TaskResultData.builder()
                .id(task.getId())
                .title(task.getTitle())
                .diary(task.getDiary())
                .build();
    }
}
