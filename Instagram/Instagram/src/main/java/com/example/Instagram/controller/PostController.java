package com.example.Instagram.controller;

import com.example.Instagram.dao.UserRepository;
import com.example.Instagram.model.Post;
import com.example.Instagram.model.User;
import com.example.Instagram.service.PostService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    UserRepository userRepository;
    @PostMapping(value="save-post")
    public ResponseEntity<String>savePost(@RequestBody String postRequest){
        Post post= setPost(postRequest);
        int postId=postService.savePost(post);
        return new ResponseEntity<String>(String.valueOf(postId), HttpStatus.CREATED);

    }
public Post setPost(String postData){
    JSONObject jsonObject=new JSONObject(postData);
    User user=null;
    int userId=jsonObject.getInt("userId");

    if(userRepository.findById(userId).isPresent()){
        user=userRepository.findById(userId).get();
    }
    else{
        return null;
    }
    Post post=new Post();
    post.setUser(user);
    post.setPostDate(jsonObject.getString("postData"));
    Timestamp createdTime=new Timestamp(System.currentTimeMillis());
    post.setCreatedDate(createdTime);
    return post;
}
}
