package com.kathon.backend.repository;

import com.kathon.backend.model.Jovem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface JovemRepository extends JpaRepository<Jovem, Long> {
    Jovem findByEmailAndSenha(String email, String senha);
}
