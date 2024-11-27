package com.redesocial.gerenciador;

import com.redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios;
    private int proximoId;

    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }

    public void cadastrar(Usuario usuario) {
        usuario.setId(proximoId++);
        usuarios.add(usuario);
    }

    public Usuario buscarPorId(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public Usuario buscarPorUsername(String username) {
        return usuarios.stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    public List<Usuario> buscarPorNome(String nome) {
        return usuarios.stream().filter(u -> u.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
    }

    public void adicionarAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);
        if (usuario1 != null && usuario2 != null) {
            usuario1.adicionarAmigo(usuario2);
            usuario2.adicionarAmigo(usuario1);
        }
    }

    public void removerAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);
        if (usuario1 != null && usuario2 != null) {
            usuario1.removerAmigo(usuario2);
            usuario2.removerAmigo(usuario1);
        }
    }
}
