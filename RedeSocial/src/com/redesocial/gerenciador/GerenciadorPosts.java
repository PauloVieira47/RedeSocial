package com.redesocial.gerenciador;

import com.redesocial.modelo.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class GerenciadorPosts {
    private List<Post> posts;
    private int proximoId;

    public GerenciadorPosts() {
        this.posts = new ArrayList<>();
        this.proximoId = 1;
    }
    public void cadastrar(Post post) {
        post.setId(proximoId++); //define um id para o post
        posts.add(post); //colocar o post na lista de posts
    }
    public Post buscarPorId(int id) {
        return posts.stream()
                .filter(post -> post.getId() == id) // Filtra os posts pelo ID
                .findFirst() // Retorna o primeiro post encontrado
                .orElse(null); // Retorna null se não encontrar
    }
    public List<Post> listarPorUsuario(int idUsuario) {
        return posts.stream()
                .filter(post -> post.getAutor().getId() == idUsuario) //filtrar o ID do autor
                .collect(Collectors.toList()); //retorna os posts como uma lista
    }
    public List<Post> listarTodos() {
        return new ArrayList<>(posts); //pra retornar a copia da lista dos post
    }
    public boolean excluir(int id) {
        return posts.removeIf(post -> post.getId() == id); //remove o post se o id tiver errado
    }
}
