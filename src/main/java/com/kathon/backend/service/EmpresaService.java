package com.kathon.backend.service;

import com.kathon.backend.model.Empresa;
import com.kathon.backend.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    // Método para cadastrar a empresa
    public Empresa cadastrarEmpresa(Empresa empresa) {
        // Aqui você pode adicionar validações antes de salvar
        return empresaRepository.save(empresa);
    }

    // Método para buscar empresa por email e senha (para login)
    public Empresa buscarEmpresaPorEmailESenha(String email, String senha) {
        return empresaRepository.findByEmailCorporativoAndSenha(email, senha);
    }

    // Método para retornar as informações da empresa no login (nome, CNPJ e imagem em Base64)
    public Empresa buscarInformacoesDaEmpresaPorEmailESenha(String email, String senha) {
        Empresa empresa = empresaRepository.findByEmailCorporativoAndSenha(email, senha);
        if (empresa != null) {
            // Ao retornar a empresa, convertemos a foto para Base64
            empresa.setFotoPerfilBase64(empresa.getFotoPerfilBase64());
        }
        return empresa;
    }
}
