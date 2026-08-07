package com.SocialNetwork.SocialNetwork.repository;

import com.SocialNetwork.SocialNetwork.model.Like;
import com.SocialNetwork.SocialNetwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPost(Post post);
}
