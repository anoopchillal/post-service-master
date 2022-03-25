package com.example.postservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {
    @Id
    private String postID;
    @NotEmpty(message = "post is required")
    private String post;
    @NotEmpty(message = "postedBy ID Object is required")
    private  User postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int commentCounts;
    private int likeCounts;
}
