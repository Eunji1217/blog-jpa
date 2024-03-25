package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.dto.CommentRequest;
import com.estsoft.blogjpa.dto.CommentResponse;
import com.estsoft.blogjpa.model.Article;
import com.estsoft.blogjpa.model.Comment;
import com.estsoft.blogjpa.service.BlogService;
import com.estsoft.blogjpa.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;
    private final BlogService blogService;
    public CommentController(CommentService commentService,BlogService blogService) {
        this.commentService = commentService;
        this.blogService = blogService;
    }

    @PostMapping("/comments/{articleId}")
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest request
            , @PathVariable Long articleId){
        CommentResponse response = commentService.saveComment(request,articleId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("/comments/{articleId}/{commentId}")
    public ResponseEntity<CommentResponse> showComment(@PathVariable Long commentId){
        CommentResponse response = commentService.findById(commentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @GetMapping("/comments/{articleId}")
    public ResponseEntity<ArticleResponse> showArticle(@PathVariable Long articleId){
        Article article = blogService.findById(articleId);
        ArticleResponse response = article.toResponse();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }
}
