package com.SocialNetwork.SocialNetwork.repository;

import com.SocialNetwork.SocialNetwork.model.Like;
import com.SocialNetwork.SocialNetwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPost(Post post);
}
