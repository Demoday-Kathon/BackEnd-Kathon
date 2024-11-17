package com.kathon.backend.controller;

import com.kathon.backend.model.Post;
import com.kathon.backend.model.Jovem;
import com.kathon.backend.repository.PostRepository;
import com.kathon.backend.repository.JovemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JovemRepository jovemRepository;

    // Endpoint para criar um post
    @PostMapping("/criar")
    public ResponseEntity<?> criarPost(
            @RequestParam("idJovem") Long idJovem,
            @RequestParam("descricao") String descricao,
            @RequestParam(value = "imagemPost", required = false) MultipartFile imagemPost) throws IOException {

        // Recuperar o Jovem a partir do ID
        Optional<Jovem> jovemOpt = jovemRepository.findById(idJovem);
        if (!jovemOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Jovem não encontrado.");
        }

        Jovem jovem = jovemOpt.get();

        // Salvar a imagem (se houver)
        byte[] imagemPostBytes = null;
        if (imagemPost != null && !imagemPost.isEmpty()) {
            imagemPostBytes = imagemPost.getBytes();
        }

        // Criar o post e associar ao jovem
        Post post = new Post();
        post.setJovem(jovem); // Associando o jovem ao post
        post.setDescricao(descricao);
        post.setImagemPost(imagemPostBytes);

        // Salvar o post no banco de dados
        postRepository.save(post);

        return ResponseEntity.ok().body("{\"success\": true}");
    }

    // Endpoint para listar todos os posts
    @GetMapping("/listar")
    public ResponseEntity<List<Post>> listarPosts() {
        List<Post> posts = postRepository.findAll();

        // Para cada post, recuperar as informações do jovem e adicionar ao post
        for (Post post : posts) {
            Jovem jovem = post.getJovem();
            post.setDescricao(jovem.getNomeCompleto() + ": " + post.getDescricao());
            // Aqui, você pode adicionar mais informações do jovem, como a foto, por exemplo
        }

        return ResponseEntity.ok(posts);
    }
}
