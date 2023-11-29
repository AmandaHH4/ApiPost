package com.post.apiBlamovipost.controllers;

import com.post.apiBlamovipost.dtos.PostDto;
import com.post.apiBlamovipost.models.PostModel;
import com.post.apiBlamovipost.repositories.ComentarioRepository;
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
@RequestMapping(value = "/comentario", produces = {"application/json"})
public class ComentarioController {

    @Autowired
    ComentarioRepository comentarioRepository;

    @GetMapping
    public ResponseEntity<List<PostModel>> ListarComentario() {
        return ResponseEntity.status(HttpStatus.OK).body(comentarioRepository.findAll());
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<Object> buscarComentario(@PathVariable(value = "idComentario") UUID id) {
        Optional<PostModel> postBuscado = comentarioRepository.findById(id);

        if (postBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(postBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarComentario(@RequestBody @Valid ComentarioDto dadosRecebidos) {
//        if (postRepository.finByPost(dadosRecebidos.texto()) != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
//        }
        PostModel postModel = new PostModel();
        BeanUtils.copyProperties(dadosRecebidos, comentarioModel);


        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioRepository.save(postModel));
    }




}
