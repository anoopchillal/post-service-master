package com.example.postservice.controller;

import com.example.postservice.dto.PostDto;
import com.example.postservice.model.Post;
import com.example.postservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
  @Autowired
  private PostService postService;

  @GetMapping("/{postId}")
  public ResponseEntity<PostDto> findById(@PathVariable("postId") String postId){
    return new ResponseEntity<>(postService.findById(postId), HttpStatus.ACCEPTED);
  }

  @PutMapping("/{postId}")
  public ResponseEntity<Post> update(@Valid @RequestBody Post post, @PathVariable("postId") String postId){
    return  new ResponseEntity<>(postService.update(post,postId), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<String> deleteId(@PathVariable("postId") String postId){
    return  new ResponseEntity<>(postService.deleteId(postId), HttpStatus.ACCEPTED);
  }

  @GetMapping()
  public ResponseEntity<List<PostDto>> allUser(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize){
    return new ResponseEntity<>(postService.allUser(page,pageSize), HttpStatus.ACCEPTED);
  }

  @PostMapping("/posts")
  public ResponseEntity<Post> userPost(@RequestBody @Valid Post post){
    return  new ResponseEntity<>(postService.userPost(post), HttpStatus.ACCEPTED);
  }

}
