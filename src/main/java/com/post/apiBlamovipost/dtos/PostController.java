package com.post.apiBlamovipost.dtos;

import com.post.apiBlamovipost.models.PostModel;
import com.post.apiBlamovipost.repositories.PostRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Provider;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/post", produces = {"application/json"})
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping
    public ResponseEntity<List<PostModel>> ListarPost(){
    return ResponseEntity.status(HttpStatus.OK).body(postRepository.findAll());
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<Object> exibirpost(@PathVariable)

}
