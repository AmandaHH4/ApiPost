package com.post.apiBlamovipost.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record PostDto(
        String quantidadeCurtida,
        String texto,





        // com imagem
        MultipartFile img
) {



}
