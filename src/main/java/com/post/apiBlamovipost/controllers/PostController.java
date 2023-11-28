package com.post.apiBlamovipost.controllers;

import com.post.apiBlamovipost.dtos.PostDto;
import com.post.apiBlamovipost.models.PostModel;
import com.post.apiBlamovipost.repositories.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/post", produces = {"application/json"})
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping
    public ResponseEntity<List<PostModel>> ListarPost() {
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.findAll());
    }

    //  @GetMapping("/{idPost}")
    // public ResponseEntity<Object> exibirpost(@PathVariable( value = "id_Post") UUID id){

    @GetMapping("/{idPost}")
    public ResponseEntity<Object> buscarPostid(@PathVariable(value = "idPost") UUID id) {
        Optional<PostModel> postBuscado = postRepository.findById(id);

        if (postBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post nao encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(postBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarPost(@RequestBody @Valid PostDto dadosRecebidos) {
//        if (postRepository.finByPost(dadosRecebidos.texto()) != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
//        }
        PostModel postModel = new PostModel();
        BeanUtils.copyProperties(dadosRecebidos, postModel);


        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(postModel));
    }
}
