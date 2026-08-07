package com.SocialNetwork.SocialNetwork.service;

import com.SocialNetwork.SocialNetwork.model.Utente;
import com.SocialNetwork.SocialNetwork.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository utenteRepository;

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
}
