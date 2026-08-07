package com.SocialNetwork.SocialNetwork.runner;

import com.SocialNetwork.SocialNetwork.model.Commento;
import com.SocialNetwork.SocialNetwork.model.Post;
import com.SocialNetwork.SocialNetwork.model.Utente;
import com.SocialNetwork.SocialNetwork.service.CommentoService;
import com.SocialNetwork.SocialNetwork.service.LikeService;
import com.SocialNetwork.SocialNetwork.service.PostService;
import com.SocialNetwork.SocialNetwork.service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UtenteService utenteService;
    private final PostService postService;
    private final CommentoService commentoService;
    private final LikeService likeService;

    @Override
    public void run(String... args) throws Exception {

        // --- Utenti ---
        Utente mario = utenteService.save(Utente.builder()
                .username("mario_rossi")
                .nomeCompleto("Mario Rossi")
                .email("mario@example.com")
                .build());

        Utente giulia = utenteService.save(Utente.builder()
                .username("giulia_bianchi")
                .nomeCompleto("Giulia Bianchi")
                .email("giulia@example.com")
                .build());

        Utente luca = utenteService.save(Utente.builder()
                .username("luca_verdi")
                .nomeCompleto("Luca Verdi")
                .email("luca@example.com")
                .build());

        System.out.println("--- Utenti creati ---");
        utenteService.findAll().forEach(u ->
                System.out.println(u.getId() + " | " + u.getUsername() + " | " + u.getEmail()));

        // --- Post ---
        Post post1 = postService.save(Post.builder()
                .testo("Primo post di Mario!")
                .dataPubblicazione(LocalDateTime.now())
                .autore(mario)
                .build());

        Post post2 = postService.save(Post.builder()
                .testo("Giulia condivide i suoi pensieri.")
                .dataPubblicazione(LocalDateTime.now())
                .autore(giulia)
                .build());

        System.out.println("\n--- Post creati ---");
        postService.findAll().forEach(p ->
                System.out.println(p.getId() + " | " + p.getAutore().getUsername() + ": " + p.getTesto()));

        // --- Commenti ---
        commentoService.save(Commento.builder()
                .testo("Bel post Mario!")
                .data(LocalDateTime.now())
                .autore(giulia)
                .post(post1)
                .build());

        commentoService.save(Commento.builder()
                .testo("Sono d'accordo con Giulia.")
                .data(LocalDateTime.now())
                .autore(luca)
                .post(post1)
                .build());

        System.out.println("\n--- Commenti sul post1 ---");
        commentoService.findByPost(post1).forEach(c ->
                System.out.println(c.getAutore().getUsername() + ": " + c.getTesto()));

        // --- Like ---
        likeService.addLike(giulia, post1);
        likeService.addLike(luca, post1);
        likeService.addLike(mario, post2);

        System.out.println("\n--- Like sul post1 ---");
        likeService.findByPost(post1).forEach(l ->
                System.out.println("Like di: " + l.getUtente().getUsername()));

        // --- Test like duplicato ---
        System.out.println("\n--- Test like duplicato ---");
        try {
            likeService.addLike(giulia, post1);
        } catch (RuntimeException e) {
            System.out.println("Eccezione corretta: " + e.getMessage());
        }

        // --- [EXTRA] Eliminazione utente con post, commenti e like associati ---
        System.out.println("\n--- [EXTRA] Eliminazione utente mario con dati associati ---");
        utenteService.delete(mario.getId());
        System.out.println("Utente mario eliminato correttamente.");
        System.out.println("Utenti rimasti: " + utenteService.findAll().size());
    }
}
