package com.SocialNetwork.SocialNetwork.service;

import com.SocialNetwork.SocialNetwork.model.Post;
import com.SocialNetwork.SocialNetwork.model.Utente;
import com.SocialNetwork.SocialNetwork.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post non trovato con id: " + id));
    }

    public List<Post> findByAutore(Utente autore) {
        return postRepository.findByAutore(autore);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
