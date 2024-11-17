package com.kathon.backend.model;

import jakarta.persistence.*;
import java.util.Base64;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEmpresa;
    private String emailCorporativo;
    private String cnpj;
    private String inscricaoEstadual;
    private String celular;
    private String cep;
    private String rua;
    private String numero;
    private String cidade;
    private String bairro;
    private String estado;
    private String senha;

    @Lob
    private byte[] fotoPerfil; // Foto de Perfil da Empresa

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    // Método para retornar a foto como Base64
    public String getFotoPerfilBase64() {
        if (this.fotoPerfil != null) {
            return Base64.getEncoder().encodeToString(this.fotoPerfil);
        }
        return null; // Retorna null caso a fotoPerfil seja null
    }

    // Método para definir a foto a partir de uma string Base64
    public void setFotoPerfilBase64(String fotoPerfilBase64) {
        if (fotoPerfilBase64 != null && !fotoPerfilBase64.isEmpty()) {
            try {
                this.fotoPerfil = Base64.getDecoder().decode(fotoPerfilBase64);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Formato Base64 inválido para fotoPerfil", e);
            }
        }
    }
}
