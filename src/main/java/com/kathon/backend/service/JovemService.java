package com.kathon.backend.service;

import com.kathon.backend.model.Jovem;
import com.kathon.backend.repository.JovemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JovemService {

    @Autowired
    private JovemRepository jovemRepository;

    public Jovem cadastrarJovem(Jovem jovem) {
        return jovemRepository.save(jovem);
    }

    public Jovem buscarJovemPorEmailESenha(String email, String senha) {
        return jovemRepository.findByEmailAndSenha(email, senha);
    }
}
