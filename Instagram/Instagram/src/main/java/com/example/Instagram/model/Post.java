package com.example.Instagram.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    private Integer postId;
    @Column(name="created_date")
    private Timestamp createdDate;
    @Column(name="update_date")
    private Timestamp updatedDate;
    @Column(name="post_date")
    private String postDate;
    @JoinColumn(name="user_id")
    @ManyToOne
    private User user;

}
