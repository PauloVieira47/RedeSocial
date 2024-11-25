package com.redesocial.gerenciador;

import com.redesocial.modelo.Post;
import com.redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorPosts {
    private List<Post> posts; // Lista de todos os posts no sistema
    private int proximoId; // Controle para o ID único dos posts

    /**
     * Construtor da classe GerenciadorPosts.
     * Inicializa a lista de posts e define o próximo ID como 1.
     */
    public GerenciadorPosts() {
        this.posts = new ArrayList<>();
        this.proximoId = 1;
    }

    /**
     * Cria um novo post, atribuindo um ID único e adicionando-o à lista.
     * @param post O post a ser criado.
     */
    public void criar(Post post) {
        post.setId(proximoId++); // Define um ID único para o post
        posts.add(post); // Adiciona o post à lista
    }

    /**
     * Busca um post pelo ID.
     * @param id O ID do post a ser buscado.
     * @return O post encontrado ou null se não existir.
     */
    public Post buscarPorId(int id) {
        return posts.stream()
                .filter(post -> post.getId() == id) // Filtra os posts pelo ID
                .findFirst() // Retorna o primeiro post encontrado
                .orElse(null); // Retorna null se não encontrar
    }

    /**
     * Lista todos os posts de um usuário específico.
     * @param idUsuario O ID do autor dos posts.
     * @return Uma lista de posts criados pelo usuário.
     */
    public List<Post> listarPorUsuario(int idUsuario) {
        return posts.stream()
                .filter(post -> post.getAutor().getId() == idUsuario) // Filtra pelo ID do autor
                .collect(Collectors.toList()); // Retorna a lista de posts
    }

    /**
     * Lista todos os posts existentes no sistema.
     * @return Uma cópia da lista de posts.
     */
    public List<Post> listarTodos() {
        return new ArrayList<>(posts); // Retorna uma cópia da lista de posts
    }

    /**
     * Exclui um post com base no ID.
     * @param id O ID do post a ser excluído.
     * @return true se o post foi excluído, false caso contrário.
     */
    public boolean excluir(int id) {
        return posts.removeIf(post -> post.getId() == id); // Remove o post se o ID coincidir
    }

    /**
     * Adiciona uma curtida a um post.
     * @param idPost O ID do post a ser curtido.
     * @param usuario O usuário que está curtindo o post.
     */
    public void curtir(int idPost, Usuario usuario) {
        Post post = buscarPorId(idPost);
        if (post != null) {
            post.adicionarCurtida(usuario);
        } else {
            throw new IllegalArgumentException("Post não encontrado.");
        }
    }

    /**
     * Remove uma curtida de um post.
     * @param idPost O ID do post cuja curtida será removida.
     * @param usuario O usuário que está removendo a curtida.
     */
    public void descurtir(int idPost, Usuario usuario) {
        Post post = buscarPorId(idPost);
        if (post != null) {
            post.removerCurtida(usuario);
        } else {
            throw new IllegalArgumentException("Post não encontrado.");
        }
    }
}
