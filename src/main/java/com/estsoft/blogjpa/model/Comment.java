package com.estsoft.blogjpa.model;

import com.estsoft.blogjpa.dto.CommentResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name="body")
    private String body;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Comment(String body,Article article){
        this.article = article;
        this.body = body;
        this.createdAt = LocalDateTime.now();
    }
    public CommentResponse toResponse(){
        return CommentResponse.builder()
                .body(body)
                .created_at(createdAt)
                .build();
    }
}
