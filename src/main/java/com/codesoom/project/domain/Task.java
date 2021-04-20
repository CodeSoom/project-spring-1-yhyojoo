package com.codesoom.project.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 할 일 정보.
 */
@Getter
@NoArgsConstructor
@Entity
public class Task {
    @Id @GeneratedValue
    @Column(name = "TASK_ID")
    private Long id;

    @Setter
    private String title;

    @ManyToOne
    @JoinColumn(name = "DIARY_ID")
    private Diary diary;

    public void setDiary(Diary diary) {
        this.diary = diary;

        if (!diary.getTasks().contains(this)) {
            diary.addTask(this);
        }
    }

    @Builder
    public Task(Long id, String title, Diary diary) {
        this.id = id;
        this.title = title;
        this.diary = diary;
    }

    @Builder
    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void updateWith(Task source) {
        this.title = source.getTitle();
    }
}
