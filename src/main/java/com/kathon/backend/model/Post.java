package com.kathon.backend.model;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "jovem_id", referencedColumnName = "id", nullable = false)
    private Jovem jovem;  // Relacionamento com o modelo Jovem

    private String descricao;

    @Lob  // Indica que o campo imagemPost será tratado como um LOB (Large Object)
    @Column(columnDefinition = "LONGBLOB") // Garantindo que o tipo da coluna no banco de dados será LONGBLOB
    private byte[] imagemPost; // Usado para armazenar imagem (LONGBLOB no MySQL)

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Jovem getJovem() {
        return jovem;
    }

    public void setJovem(Jovem jovem) {
        this.jovem = jovem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagemPost() {
        return imagemPost;
    }

    public void setImagemPost(byte[] imagemPost) {
        this.imagemPost = imagemPost;
    }
}
