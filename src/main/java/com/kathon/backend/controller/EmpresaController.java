package com.kathon.backend.controller;

import com.kathon.backend.model.Empresa;
import com.kathon.backend.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Endpoint para cadastrar uma nova empresa
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarEmpresa(@RequestParam String nomeEmpresa,
                                                   @RequestParam String emailCorporativo,
                                                   @RequestParam String cnpj,
                                                   @RequestParam String senha,
                                                   @RequestParam MultipartFile fotoPerfil) throws IOException {
        byte[] fotoPerfilBytes = null;
        if (fotoPerfil != null) {
            // Converter a foto para um array de bytes diretamente
            fotoPerfilBytes = fotoPerfil.getBytes();
        }

        // Criar o objeto Empresa e configurar os dados
        Empresa empresa = new Empresa();
        empresa.setNomeEmpresa(nomeEmpresa);
        empresa.setEmailCorporativo(emailCorporativo);
        empresa.setCnpj(cnpj);
        empresa.setSenha(senha);
        empresa.setFotoPerfil(fotoPerfilBytes); // Salvar foto de perfil no banco de dados

        // Salvar a empresa no banco de dados
        empresaService.cadastrarEmpresa(empresa);

        return ResponseEntity.status(HttpStatus.CREATED).body("Empresa cadastrada com sucesso");
    }

    // Endpoint para login da empresa
    @PostMapping("/login")
    public ResponseEntity<Object> loginEmpresa(@RequestParam String emailCorporativo, @RequestParam String senha) {
        Empresa empresa = empresaService.buscarEmpresaPorEmailESenha(emailCorporativo, senha);
        if (empresa != null) {
            // Criar uma resposta com as informações da empresa
            EmpresaLoginResponse response = new EmpresaLoginResponse();
            response.setNomeEmpresa(empresa.getNomeEmpresa());
            response.setCnpj(empresa.getCnpj());
            response.setFotoPerfilBase64(empresa.getFotoPerfilBase64());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    // DTO para retorno das informações após login
    public static class EmpresaLoginResponse {
        private String nomeEmpresa;
        private String cnpj;
        private String fotoPerfilBase64;

        public String getNomeEmpresa() {
            return nomeEmpresa;
        }

        public void setNomeEmpresa(String nomeEmpresa) {
            this.nomeEmpresa = nomeEmpresa;
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public String getFotoPerfilBase64() {
            return fotoPerfilBase64;
        }

        public void setFotoPerfilBase64(String fotoPerfilBase64) {
            this.fotoPerfilBase64 = fotoPerfilBase64;
        }
    }
}
