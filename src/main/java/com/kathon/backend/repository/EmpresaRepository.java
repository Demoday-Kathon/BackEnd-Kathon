package com.kathon.backend.repository;

import com.kathon.backend.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    // Método para buscar empresa por email e senha
    Empresa findByEmailCorporativoAndSenha(String emailCorporativo, String senha);
}
