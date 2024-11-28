package com.redesocial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private Integer id;
    private Usuario autor; // O usuário que criou o post
    private String conteudo; // Conteúdo do post
    private LocalDateTime dataPublicacao; // Data de publicação do post
    private List<Usuario> curtidas; // Lista de usuários que curtiram o post
    private List<Comentario> comentarios; // Lista de comentários no post

    // Construtor do post
    public Post(Usuario autor, String conteudo) {
        if (conteudo == null || conteudo.trim().isEmpty()) {
            throw new IllegalArgumentException("Conteúdo do post não pode ser vazio.");
        }

        if (conteudo.length() > 500) {
            throw new IllegalArgumentException("Conteúdo do post não pode ter mais de 500 caracteres.");
        }

        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = LocalDateTime.now();
        this.curtidas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        if (conteudo == null || conteudo.trim().isEmpty()) {
            throw new IllegalArgumentException("Conteúdo do post não pode ser vazio.");
        }

        if (conteudo.length() > 500) {
            throw new IllegalArgumentException("Conteúdo do post não pode ter mais de 500 caracteres.");
        }

        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    // Métodos para interagir com o post
    public void adicionarCurtida(Usuario usuario) {
        if (!curtidas.contains(usuario)) {
            curtidas.add(usuario);
        }
    }

    public void removerCurtida(Usuario usuario) {
        curtidas.remove(usuario);
    }

    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public void removerComentario(Comentario comentario) {
        comentarios.remove(comentario);
    }

    @Override
    public String toString() {
        return "Post{" +
                "autor=" + autor.getUsername() +
                ", conteudo='" + conteudo + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                '}';
    }
}
