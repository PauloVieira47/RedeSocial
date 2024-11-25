package com.redesocial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa um usuário da rede social.
 */
public class Usuario {
    private Integer id;
    private String nome;
    private String username;
    private String email;
    private String senha;  // Atributo senha
    private LocalDateTime dataCadastro;
    private List<Usuario> amigos;
    private List<Post> posts;

    /**
     * Construtor da classe Usuario.
     * @param nome Nome do usuário.
     * @param username Nome de usuário (username) único.
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     */
    public Usuario(String nome, String username, String email, String senha) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now();
        this.amigos = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;  // Método getter para senha
    }

    public void setSenha(String senha) {
        this.senha = senha;  // Método setter para senha
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public List<Post> getPosts() {
        return posts;
    }

    // Métodos relacionados aos amigos
    /**
     * Adiciona um amigo à lista de amigos do usuário.
     * @param amigo O usuário a ser adicionado como amigo.
     */
    public void adicionarAmigo(Usuario amigo) {
        if (!amigos.contains(amigo)) {
            amigos.add(amigo);
        }
    }

    /**
     * Remove um amigo da lista de amigos do usuário.
     * @param amigo O usuário a ser removido da lista de amigos.
     */
    public void removerAmigo(Usuario amigo) {
        amigos.remove(amigo);
    }

    // Métodos relacionados aos posts
    /**
     * Adiciona um post à lista de posts do usuário.
     * @param post O post a ser adicionado.
     */
    public void adicionarPost(Post post) {
        posts.add(post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
