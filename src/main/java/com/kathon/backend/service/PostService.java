package com.kathon.backend.service;

import com.kathon.backend.model.Jovem;
import com.kathon.backend.model.Post;
import com.kathon.backend.repository.JovemRepository;
import com.kathon.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JovemRepository jovemRepository;

    // Criar um post
    public Post criarPost(Post post) {
        Jovem jovem = jovemRepository.findById(post.getJovem().getId())
                .orElseThrow(() -> new RuntimeException("Jovem não encontrado"));

        // Associa o jovem ao post
        post.setJovem(jovem);

        // Salva o post
        return postRepository.save(post);
    }

    // Listar todos os posts
    public List<Post> listarPosts() {
        return postRepository.findAll();
    }
}