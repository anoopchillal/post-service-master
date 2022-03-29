package com.example.postservice.services;

import com.example.postservice.constants.Constants;
import com.example.postservice.dto.PostDto;
import com.example.postservice.exception.PostNotFoundException;
import com.example.postservice.feign.CommentService;
import com.example.postservice.feign.LikeService;
import com.example.postservice.feign.UserService;
import com.example.postservice.model.Post;
import com.example.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
public class PostService {

  @Autowired
  private PostRepo postRepo;

  @Autowired
  private UserService userFeign;

  @Autowired
  private LikeService likeFeign;

  @Autowired
  private CommentService commentFeign;


  public PostDto findById(String postId){


    Post post=postRepo.findById(postId).get();

    return new PostDto(post.getPostID(),post.getPost(),
            userFeign.findByID(post.getPostedBy())
            ,post.getCreatedAt(),post.getUpdatedAt(),likeFeign.likeCount(post.getPostID()),
            commentFeign.commentCount(post.getPostID()));



  }

  public Post update(Post post, String postId) {
    post.setUpdatedAt(LocalDateTime.now());
    return postRepo.save(post);
  }

  public String deleteId(String Id) {
    postRepo.deleteById(Id);
    return "Post" + Id + " Deleted Successfully";
  }

  public List<PostDto> allUser(Integer page, Integer pageSize) {
    if(page==null){
      page=1;
    }
    if(pageSize==null){
      pageSize=10;
    }
    Pageable firstPage = PageRequest.of(page-1, pageSize);
    List<Post> posts= postRepo.findAll(firstPage).toList();
    if(posts.isEmpty()){
      throw new PostNotFoundException(Constants.POST_NOT_FOUND);
    }
    List<PostDto> postDTOS=new ArrayList<>();
    for(Post post:posts){
       PostDto  postDTO = new PostDto(post.getPostID(),post.getPost(),
              userFeign.findByID(post.getPostedBy()),post.getCreatedAt(),
              post.getUpdatedAt(),likeFeign.likeCount(post.getPostID()),
              commentFeign.commentCount(post.getPostID()));
      postDTOS.add(postDTO);
    }
    return  postDTOS;
  }

  public Post userPost(Post post) {
    post.setCreatedAt(LocalDateTime.now());
    post.setUpdatedAt(LocalDateTime.now());
    return postRepo.save(post);
  }
}
