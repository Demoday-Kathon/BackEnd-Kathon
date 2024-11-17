package com.kathon.backend.controller;

import com.kathon.backend.model.Jovem;
import com.kathon.backend.service.JovemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import java.text.ParseException;

@RestController
@RequestMapping("/api/jovens")
public class JovemController {

    @Autowired
    private JovemService jovemService;

    // Cadastro de jovem, incluindo foto de perfil
    @PostMapping("/cadastrar")
    public Jovem cadastrarJovem(
            @RequestParam("nomeCompleto") String nomeCompleto,
            @RequestParam("email") String email,
            @RequestParam("cpf") String cpf,
            @RequestParam("dataNascimento") String dataNascimento,
            @RequestParam("celular") String celular,
            @RequestParam("cep") String cep,
            @RequestParam("rua") String rua,
            @RequestParam("numero") String numero,
            @RequestParam("cidade") String cidade,
            @RequestParam("bairro") String bairro,
            @RequestParam("estado") String estado,
            @RequestParam("senha") String senha,
            @RequestParam("documentoHistorico") MultipartFile documentoHistorico,
            @RequestParam("fotoPerfil") MultipartFile fotoPerfil) {

        Jovem jovem = new Jovem();
        jovem.setNomeCompleto(nomeCompleto);
        jovem.setEmail(email);
        jovem.setCpf(cpf);

        // Convertendo a data de nascimento
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date data = format.parse(dataNascimento);
            jovem.setDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();  // Tratar erro de data
        }

        jovem.setCelular(celular);
        jovem.setCep(cep);
        jovem.setRua(rua);
        jovem.setNumero(numero);
        jovem.setCidade(cidade);
        jovem.setBairro(bairro);
        jovem.setEstado(estado);
        jovem.setSenha(senha);

        try {
            // Convertendo o documento histórico e a foto para byte[]
            jovem.setDocumentoHistorico(documentoHistorico.getBytes());
            jovem.setFotoPerfil(fotoPerfil.getBytes());
        } catch (IOException e) {
            e.printStackTrace();  // Tratar erro de IO
        }

        return jovemService.cadastrarJovem(jovem);
    }

    // Endpoint de login
    @PostMapping("/login")
    public Jovem login(@RequestParam String email, @RequestParam String senha) {
        return jovemService.buscarJovemPorEmailESenha(email, senha);
    }
}

