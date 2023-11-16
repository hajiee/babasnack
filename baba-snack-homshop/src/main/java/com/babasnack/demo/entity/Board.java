package com.babasnack.demo.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private Long bno;
    private String title;
    private String boardNotice;
    private String boardWriter;
    private LocalDate boardDate = LocalDate.now();
    private boolean boardState;
    private Integer boardCode;
    private List<BoardReview> boardReviews;

    public boolean getBoardState() {
        return boardState;
    }

    public void setBoardState(boolean boardState) {
        this.boardState = boardState;
    }
}
