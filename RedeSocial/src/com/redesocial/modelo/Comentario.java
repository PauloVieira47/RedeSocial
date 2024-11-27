package com.redesocial.modelo;

import java.time.LocalDateTime;

/**
 * Classe que representa um comentário feito em um post.
 */
public class Comentario {
    private Integer id;
    private Usuario autor;
    private String conteudo;
    private LocalDateTime dataComentario;
    private Post post;

    /**
     * Construtor para criar um comentário.
     * @param autor Autor do comentário.
     * @param conteudo Conteúdo do comentário.
     * @param post Post associado.
     */
    public Comentario(Usuario autor, String conteudo, Post post) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataComentario = LocalDateTime.now();
        this.post = post;
    }

    // Getters
    public Usuario getAutor() {
        return autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataComentario() {
        return dataComentario;
    }

    public Post getPost() {
        return post;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "autor=" + autor.getUsername() +
                ", conteudo='" + conteudo + '\'' +
                ", dataComentario=" + dataComentario +
                '}';
    }
}
