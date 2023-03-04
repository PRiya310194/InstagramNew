package com.example.Instagram.dao;

import com.example.Instagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface POstRepository extends JpaRepository<Post,Integer> {

}
