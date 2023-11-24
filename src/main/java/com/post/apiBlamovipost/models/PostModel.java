package com.post.apiBlamovipost.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "post")
public class PostModel implements Serializable {

    @Serial
    private static final long serialVersinUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_post", nullable = false)
    private int id;


    private int quantidadeCurtida;
    private String texto;
    private String nota;


    //@ManyToOne
    //@JoinColumn(name = "id_Video", referencedColumnName = "video")
    private int id_video;

    //@ManyToOne
    //@JoinColumn(name = "id_Usuario", referencedColumnName = "usuario")
    private int id_usuario;

    //@ManyToOne
    //@JoinColumn(name = "id_Comentario", referencedColumnName = "video")
    private int id_comentario;





}


