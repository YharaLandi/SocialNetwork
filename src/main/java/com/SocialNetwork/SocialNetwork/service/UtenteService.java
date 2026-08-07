package com.SocialNetwork.SocialNetwork.service;

import com.SocialNetwork.SocialNetwork.model.Post;
import com.SocialNetwork.SocialNetwork.model.Utente;
import com.SocialNetwork.SocialNetwork.repository.CommentoRepository;
import com.SocialNetwork.SocialNetwork.repository.LikeRepository;
import com.SocialNetwork.SocialNetwork.repository.PostRepository;
import com.SocialNetwork.SocialNetwork.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final PostRepository postRepository;
    private final CommentoRepository commentoRepository;
    private final LikeRepository likeRepository;

    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente findById(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + id));
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con username: " + username));
    }

    public Utente update(Long id, Utente datiAggiornati) {
        Utente utente = findById(id);
        utente.setUsername(datiAggiornati.getUsername());
        utente.setNomeCompleto(datiAggiornati.getNomeCompleto());
        utente.setEmail(datiAggiornati.getEmail());
        return utenteRepository.save(utente);
    }

    public void delete(Long id) {
        Utente utente = findById(id);

        // elimina i like messi dall'utente su post altrui
        likeRepository.deleteAll(likeRepository.findByUtente(utente));

        // elimina i commenti scritti dall'utente su post altrui
        commentoRepository.deleteAll(commentoRepository.findByAutore(utente));

        // per ogni post dell'utente: elimina prima i like e commenti ricevuti
        List<Post> posts = postRepository.findByAutore(utente);
        posts.forEach(post -> {
            likeRepository.deleteAll(likeRepository.findByPost(post));
            commentoRepository.deleteAll(commentoRepository.findByPost(post));
        });

        postRepository.deleteAll(posts);
        utenteRepository.delete(utente);
    }
}
