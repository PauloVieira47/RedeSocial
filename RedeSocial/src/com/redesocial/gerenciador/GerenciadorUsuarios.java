package com.redesocial.gerenciador;

import com.redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável pelo gerenciamento de usuários.
 * Permite cadastrar, buscar e gerenciar amizades entre usuários.
 */
public class GerenciadorUsuarios {
    private List<Usuario> usuarios; // Lista de usuários cadastrados
    private int proximoId; // Controle para IDs únicos dos usuários

    /**
     * Construtor da classe GerenciadorUsuarios.
     * Inicializa a lista de usuários e define o próximo ID como 1.
     */
    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }

    /**
     * Cadastra um novo usuário, atribuindo um ID único.
     * @param usuario O usuário a ser cadastrado.
     */
    public void cadastrar(Usuario usuario) {
        usuario.setId(proximoId++);
        usuarios.add(usuario);
    }

    /**
     * Busca um usuário pelo ID.
     * @param id O ID do usuário a ser buscado.
     * @return O usuário encontrado ou null se não existir.
     */
    public Usuario buscarPorId(int id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca um usuário pelo username.
     * @param username O username do usuário a ser buscado.
     * @return O usuário encontrado ou null se não existir.
     */
    public Usuario buscarPorUsername(String username) {
        return usuarios.stream()
                .filter(usuario -> usuario.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca usuários pelo nome.
     * @param nome O nome (ou parte do nome) a ser buscado.
     * @return Uma lista de usuários cujo nome contém o texto especificado.
     */
    public List<Usuario> buscarPorNome(String nome) {
        return usuarios.stream()
                .filter(usuario -> usuario.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Adiciona uma amizade entre dois usuários.
     * @param idUsuario1 O ID do primeiro usuário.
     * @param idUsuario2 O ID do segundo usuário.
     */
    public void adicionarAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);

        if (usuario1 == null || usuario2 == null) {
            throw new IllegalArgumentException("Um ou ambos os usuários não existem.");
        }

        if (!usuario1.getAmigos().contains(usuario2)) {
            usuario1.adicionarAmigo(usuario2);
            usuario2.adicionarAmigo(usuario1);
        }
    }

    /**
     * Remove uma amizade entre dois usuários.
     * @param idUsuario1 O ID do primeiro usuário.
     * @param idUsuario2 O ID do segundo usuário.
     */
    public void removerAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);

        if (usuario1 == null || usuario2 == null) {
            throw new IllegalArgumentException("Um ou ambos os usuários não existem.");
        }

        if (usuario1.getAmigos().contains(usuario2)) {
            usuario1.removerAmigo(usuario2);
            usuario2.removerAmigo(usuario1);
        }
    }
}
