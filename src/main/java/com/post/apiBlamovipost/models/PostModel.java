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
    @Column(name = "idPost", nullable = false)
    private UUID id;

    private int quantidadeCurtida;
    private String texto;
    private String url_img;


    // alteracao de url_img
    //@ManyToOne
    //@JoinColumn(name = "id_Video", referencedColumnName = "video")
    private UUID idVideo;

    //@ManyToOne
    //@JoinColumn(name = "id_Usuario", referencedColumnName = "usuario")
    private UUID idUsuario;




    /// acrescentado para resolver erro no controller

    //@ManyToOne
    //@JoinColumn(name = "id_Comentario", referencedColumnName = "video")
    //private UUID id_comentario;





}


