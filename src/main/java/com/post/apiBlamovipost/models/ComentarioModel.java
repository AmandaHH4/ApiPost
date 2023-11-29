package com.post.apiBlamovipost.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "comentario")
public class ComentarioModel implements Serializable {

    @Serial
    private static final long serialVersinUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_comentario", nullable = false)
    private UUID comentarios;

    private String texto;


    //@ManyToOne
    //@JoinColumn(name = "id_Usuario", referencedColumnName = "usuario")
    // dando erro // private UUID id_usuario;


    //@ManyToOne
    //@JoinColumn(name = "id_comentario", referencedColumnName = "comentario")
    // dando erro // private UUID id_comentario;


}
