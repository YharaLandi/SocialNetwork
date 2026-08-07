package com.SocialNetwork.SocialNetwork.repository;

import com.SocialNetwork.SocialNetwork.model.Commento;
import com.SocialNetwork.SocialNetwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentoRepository extends JpaRepository<Commento, Long> {
    List<Commento> findByPost(Post post);
}
