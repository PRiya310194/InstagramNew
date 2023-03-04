package com.example.Instagram.service;

import com.example.Instagram.dao.POstRepository;
import com.example.Instagram.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private POstRepository pOstRepository;
    public int savePost(Post post){
        Post savePost=pOstRepository.save(post);
        return savePost.getPostId();
    }

}
