package com.estsoft.blogjpa.dto;

import com.estsoft.blogjpa.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String body;
    private LocalDateTime created_at;

    public CommentResponse(Comment comment) {
        this.body = comment.getBody();
        this.created_at = comment.getCreatedAt();
    }
}
