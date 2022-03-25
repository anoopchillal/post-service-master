package com.example.postservice.dto;

import com.example.postservice.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestFeign {
    private Post post;
    private User user;
    private int commentCounts;
    private int likeCounts;
}
