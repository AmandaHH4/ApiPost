package com.post.apiBlamovipost.controllers;

import com.post.apiBlamovipost.dtos.PostDto;
import com.post.apiBlamovipost.models.PostModel;
import com.post.apiBlamovipost.repositories.PostRepository;
import com.post.apiBlamovipost.services.FileUploadService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/post", produces = {"application/json"})
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired

    FileUploadService fileUploadService;


    @GetMapping
    public ResponseEntity<List<PostModel>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.findAll());
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<Object> buscarPost(@PathVariable(value = "idPost") UUID id) {
        Optional<PostModel> usuarioBuscado = postRepository.findById(id);

        if (usuarioBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("post não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }

    /*
    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        if (usuarioRepository.findByEmail(usuarioDto.email()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado!");
        }
        UsuarioModel novoUsuario = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(novoUsuario));
    }
    */

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> criarPost(@ModelAttribute @Valid PostDto postDto) {
//        if (usuarioRepository.findByEmail(postDto.email()) != null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado!");
//        }

        PostModel novoPost = new PostModel();
        BeanUtils.copyProperties(postDto, novoPost);

        String foto;

        try {
            foto = fileUploadService.fazerUpload(postDto.img());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        novoPost.setUrl_img(foto);
        //CRIPTOGRAFA A SENHA
//        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDto.senha());
//        novoPost.setSenha(senhaCriptografada);
//
       return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(novoPost));
    }



    @PutMapping("/{idPost}")
    public ResponseEntity<Object> editarPost(@PathVariable(value = "idPost") UUID id, @ModelAttribute @Valid PostDto postDto){
        Optional<PostModel> postBuscado = postRepository.findById(id);

        if (postBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado!");
        }
        PostModel usuarioBd = postBuscado.get();
        BeanUtils.copyProperties(postDto, usuarioBd);

        return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(usuarioBd));
    }

    @DeleteMapping("/{idPost}")
    public ResponseEntity<Object> deletarPost(@PathVariable(value = "idPost") UUID id){
        Optional<PostModel> postBuscado = postRepository.findById(id);

        if (postBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post nao encontrado");
        }

        postRepository.delete(postBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("post deletado");
    }



}

