package com.post.apiBlamovipost.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record PostDto(
        int quantidadeCurtida,
        String texto,

        UUID id_video,
        UUID id_usuario,

        MultipartFile img
){}
