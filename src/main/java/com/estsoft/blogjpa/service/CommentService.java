package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.dto.CommentRequest;
import com.estsoft.blogjpa.dto.CommentResponse;
import com.estsoft.blogjpa.model.Article;
import com.estsoft.blogjpa.model.Comment;
import com.estsoft.blogjpa.repository.BlogRepository;
import com.estsoft.blogjpa.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public CommentResponse saveComment(CommentRequest dto, Long articleId) {
        Article article = blogRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("article doesn't exist"));
        Comment comment = dto.toEntity(article);
        commentRepository.save(comment);
        CommentResponse response = comment.toResponse();
        return response;
    }
    public CommentResponse findById(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("comment doesn't exist"));
        CommentResponse response = comment.toResponse();
        return response;
    }

}
