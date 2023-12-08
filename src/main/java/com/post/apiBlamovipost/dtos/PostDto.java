package com.post.apiBlamovipost.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record PostDto(
        int quantidadeCurtida,
        String texto,

        UUID idVideo,
        UUID idUsuario,

        MultipartFile img
){}
