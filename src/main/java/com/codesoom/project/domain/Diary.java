package com.codesoom.project.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 다이어리 정보.
 */
@Getter
@NoArgsConstructor
@Entity
public class Diary {
    @Id @GeneratedValue
    @Column(name = "DIARY_ID")
    private Long id;

    @Setter
    private String title;

    @Setter
    private String comment;

    @OneToMany(mappedBy = "diary")
    private List<Task> tasks = new ArrayList<>();

    @Builder
    public Diary(Long id, String title, String comment, List<Task> tasks) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.tasks = tasks;
    }

    @Builder
    public Diary(Long id, String title, String comment) {
        this.id = id;
        this.title = title;
        this.comment = comment;
    }

    public void addTask(Task task) {
        this.tasks.add(task);

        if (task.getDiary() != this) {
            task.setDiary(this);
        }
    }

    public void updateWith(Diary source) {
        this.title = source.getTitle();
        this.comment = source.getComment();
    }
}
