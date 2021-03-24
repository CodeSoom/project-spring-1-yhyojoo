package com.codesoom.project.application;

import com.codesoom.project.domain.Diary;
import com.codesoom.project.domain.DiaryRepository;
import com.codesoom.project.errors.DiaryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DiaryServiceTest {
    private DiaryRepository diaryRepository;

    private DiaryService diaryService;

    private static final Long NOT_EXIST_ID = 100L;
    private static final Long ID = 1L;
    private static final String TITLE = "오늘의 다이어리";
    private static final String COMMENT = "아쉬운 하루였다";

    private List<Diary> diaries;
    private Diary diary;
    private Long givenValidId;
    private Long givenInvalidId;

    @BeforeEach
    void setUp() {
        diaryRepository = mock(DiaryRepository.class);

        diaryService = new DiaryService(diaryRepository);

        diaries = diaryService.getDiaries();

        diary = Diary.builder()
                .id(ID)
                .title(TITLE)
                .comment(COMMENT)
                .build();

        given(diaryRepository.findAll()).willReturn(diaries);

        given(diaryRepository.findById(ID)).willReturn(Optional.of(diary));
    }

    @Nested
    @DisplayName("getDiaries 메소드는")
    class Describe_getDiaries {

        @Nested
        @DisplayName("다이어리가 존재한다면")
        class Context_with_diary {

            @BeforeEach
            void setUp() {
                diary = new Diary();
            }

            @Test
            @DisplayName("전체 다이어리 목록을 반환한다")
            void it_returns_list() {
                diaries.add(diary);

                verify(diaryRepository).findAll();

                assertThat(diaries).hasSize(1);
            }
        }

        @Nested
        @DisplayName("다이어리가 존재하지 않는다면")
        class Context_without_diary {

            @Test
            @DisplayName("빈 목록을 반환한다")
            void it_returns_empty_list() {
                assertThat(diaries).isEmpty();
            }
        }
    }


    @Nested
    @DisplayName("getDiary 메소드는")
    class Describe_getDiary {

        @Nested
        @DisplayName("등록된 다이어리의 id가 주어진다면")
        class Context_with_valid_id {

            @BeforeEach
            void setUp() {
                givenValidId = ID;
            }

            @Test
            @DisplayName("주어진 id를 갖는 다이어리를 반환한다")
            void it_returns_diary() {
                diary = diaryService.getDiary(givenValidId);

                verify(diaryRepository).findById(givenValidId);

                assertThat(diary.getTitle()).isEqualTo(TITLE);
                assertThat(diary.getComment()).isEqualTo(COMMENT);
            }
        }

        @Nested
        @DisplayName("유효하지 않은 id가 주어진다면")
        class Context_with_Invalid_id {

            @BeforeEach
            void setUp() {
                givenInvalidId = NOT_EXIST_ID;
            }

            @Test
            @DisplayName("다이어리를 찾을 수 없다는 예외를 던진다")
            void it_returns_exception() {
                assertThatThrownBy(() -> diaryService.getDiary(givenInvalidId))
                        .isInstanceOf(DiaryNotFoundException.class);
            }
        }
    }
}
