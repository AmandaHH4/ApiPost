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

    @PostMapping (consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> cadastrarPost(@ModelAttribute @Valid PostDto dadosRecebidos) {
//        if (postRepository.finByPost(dadosRecebidos.texto()) != null) {
//     SEM IMAGEM       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
//        }
        PostModel postModel = new PostModel();
        BeanUtils.copyProperties(dadosRecebidos, postModel);

        String urlImg;

                try{
                    urlImg = fileUploadService.fazerUpload(dadosRecebidos.img());
                }catch (IOException error){
                    throw new RuntimeException(error);
                }

                postModel.setUrlImg(urlImg);

        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(postModel));
    }

   @PutMapping(value = "/{idPost}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> editarPost(@PathVariable(value = "idPost") UUID id, @ModelAttribute @Valid PostDto postDto){

       Optional<PostModel> postBuscado = postRepository.findById(id);

       if (postBuscado.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post nao encontrado");
       }


       PostModel postModel = postBuscado.get();
       BeanUtils.copyProperties(postDto, postModel);




       String urlImg;

       try{
           urlImg = fileUploadService.fazerUpload(postDto.img());
       }catch (IOException error){
           throw new RuntimeException(error);
       }

       postModel.setUrlImg(urlImg);





       return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(postModel));

   }


   @DeleteMapping("/{idPost}")

    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "idPost") UUID id){
       Optional<PostModel> postBuscado = postRepository.findById(id);

       if (postBuscado.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post nao encontrado");
       }

       postRepository.delete(postBuscado.get());
       return ResponseEntity.status(HttpStatus.OK).body("post deletado");
   }



   }


