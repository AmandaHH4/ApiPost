package com.post.apiBlamovipost.dtos;

import org.springframework.web.multipart.MultipartFile;

public record ComentarioDto(
        String texto,

        MultipartFile img
) {


}
