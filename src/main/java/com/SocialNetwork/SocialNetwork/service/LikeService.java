package com.SocialNetwork.SocialNetwork.service;

import com.SocialNetwork.SocialNetwork.model.Like;
import com.SocialNetwork.SocialNetwork.model.Post;
import com.SocialNetwork.SocialNetwork.model.Utente;
import com.SocialNetwork.SocialNetwork.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public Like addLike(Utente utente, Post post) {
        List<Like> likesDelPost = likeRepository.findByPost(post);

        boolean giaLiked = likesDelPost.stream()
                .anyMatch(l -> l.getUtente().getId().equals(utente.getId()));

        if (giaLiked) {
            throw new RuntimeException(
                    "L'utente " + utente.getUsername() + " ha già messo like a questo post"
            );
        }

        Like like = Like.builder()
                .utente(utente)
                .post(post)
                .build();

        return likeRepository.save(like);
    }

    public void removeLike(Long id) {
        likeRepository.deleteById(id);
    }

    public List<Like> findByPost(Post post) {
        return likeRepository.findByPost(post);
    }
}
