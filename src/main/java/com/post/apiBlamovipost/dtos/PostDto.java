package com.post.apiBlamovipost.dtos;

import jakarta.validation.constraints.NotBlank;

public record PostDto(
        String quantidadeCurtida,
        String texto,
        String nota
) {



}
