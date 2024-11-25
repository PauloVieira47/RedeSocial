package com.redesocial.ui;

import com.redesocial.modelo.Usuario;
import com.redesocial.modelo.Post;
import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.gerenciador.GerenciadorPosts;

import java.util.List;
import java.util.Scanner;

public class MenuUsuario {
    private final Usuario usuario;
    private final GerenciadorUsuarios gerenciadorUsuarios;
    private final GerenciadorPosts gerenciadorPosts;
    private final Scanner scanner;

    public MenuUsuario(Usuario usuario, GerenciadorUsuarios gerenciadorUsuarios, GerenciadorPosts gerenciadorPosts) {
        this.usuario = usuario;
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.gerenciadorPosts = gerenciadorPosts;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;
        do {
            System.out.println("\n=== MENU DO USUÁRIO ===");
            System.out.println("1. Criar Post");
            System.out.println("2. Ver Meu Perfil");
            System.out.println("3. Buscar Usuários");
            System.out.println("4. Gerenciar Amizades");
            System.out.println("5. Ver Feed de Notícias");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine()); // Usando nextLine() para pegar a entrada como String
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                continue; // Se houver erro na conversão, continua no loop sem quebrar o programa
            }

            switch (opcao) {
                case 1 -> criarPost();
                case 2 -> verPerfil();
                case 3 -> buscarUsuarios();
                case 4 -> gerenciarAmizades();
                case 5 -> verFeedNoticias();
                case 0 -> System.out.println("Fazendo logout...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void criarPost() {
        System.out.println("=== Criar Post ===");
        System.out.print("Digite o conteúdo do seu post: ");
        String conteudo = scanner.nextLine();

        try {
            Post post = new Post(usuario, conteudo);
            gerenciadorPosts.criar(post);
            System.out.println("Post criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar post: " + e.getMessage());
        }
    }

    private void verPerfil() {
        System.out.println("=== Meu Perfil ===");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Username: " + usuario.getUsername());
        System.out.println("Email: " + usuario.getEmail());

        System.out.println("\n=== Meus Posts ===");
        List<Post> posts = gerenciadorPosts.listarPorUsuario(usuario.getId());
        if (posts.isEmpty()) {
            System.out.println("Nenhum post encontrado.");
        } else {
            for (Post post : posts) {
                System.out.println("- " + post.getConteudo() + " (Publicado em: " + post.getDataPublicacao() + ")");
            }
        }
    }

    private void buscarUsuarios() {
        System.out.println("=== Buscar Usuários ===");
        System.out.print("Digite o nome ou username do usuário: ");
        String termo = scanner.nextLine();

        List<Usuario> usuariosEncontrados = gerenciadorUsuarios.buscarPorNome(termo);
        if (usuariosEncontrados.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            System.out.println("Usuários encontrados:");
            for (Usuario u : usuariosEncontrados) {
                System.out.println("- " + u.getNome() + " (@" + u.getUsername() + ")");
            }
        }
    }

    private void gerenciarAmizades() {
        int opcao = -1; // Inicializa com um valor inválido
        do {
            System.out.println("\n1. Ver Amigos");
            System.out.println("2. Adicionar Amigo");
            System.out.println("3. Remover Amigo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine()); // Novamente usando nextLine() e tratando exceção
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> verAmigos();
                case 2 -> adicionarAmigo();
                case 3 -> removerAmigo();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void verAmigos() {
        System.out.println("=== Lista de Amigos ===");
        List<Usuario> amigos = usuario.getAmigos();
        if (amigos.isEmpty()) {
            System.out.println("Você ainda não tem amigos.");
        } else {
            for (Usuario amigo : amigos) {
                System.out.println("- " + amigo.getNome() + " (@" + amigo.getUsername() + ")");
            }
        }
    }

    private void adicionarAmigo() {
        System.out.println("=== Adicionar Amigo ===");
        System.out.print("Digite o username do amigo: ");
        String username = scanner.nextLine();

        try {
            Usuario amigo = gerenciadorUsuarios.buscarPorUsername(username);
            if (amigo == null) {
                System.out.println("Usuário não encontrado.");
            } else if (usuario.getAmigos().contains(amigo)) {
                System.out.println("Esse usuário já é seu amigo.");
            } else if (usuario.getUsername().equals(amigo.getUsername())) {
                System.out.println("Você não pode adicionar a si mesmo como amigo.");
            } else {
                gerenciadorUsuarios.adicionarAmizade(usuario.getId(), amigo.getId());
                System.out.println("Amigo adicionado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao adicionar amigo: " + e.getMessage());
        }
    }

    private void removerAmigo() {
        System.out.println("=== Remover Amigo ===");
        System.out.print("Digite o username do amigo: ");
        String username = scanner.nextLine();

        try {
            Usuario amigo = gerenciadorUsuarios.buscarPorUsername(username);
            if (amigo == null) {
                System.out.println("Usuário não encontrado.");
            } else if (!usuario.getAmigos().contains(amigo)) {
                System.out.println("Esse usuário não está na sua lista de amigos.");
            } else {
                gerenciadorUsuarios.removerAmizade(usuario.getId(), amigo.getId());
                System.out.println("Amigo removido com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover amigo: " + e.getMessage());
        }
    }

    private void verFeedNoticias() {
        System.out.println("=== Feed de Notícias ===");
        List<Post> feed = gerenciadorPosts.listarPorUsuario(usuario.getId());
        for (Usuario amigo : usuario.getAmigos()) {
            feed.addAll(gerenciadorPosts.listarPorUsuario(amigo.getId()));
        }

        feed.sort((p1, p2) -> p2.getDataPublicacao().compareTo(p1.getDataPublicacao()));

        if (feed.isEmpty()) {
            System.out.println("Nenhuma postagem no feed.");
        } else {
            for (Post post : feed) {
                System.out.println("\nAutor: " + post.getAutor().getNome());
                System.out.println("Data: " + post.getDataPublicacao());
                System.out.println("Conteúdo: " + post.getConteudo());
                System.out.println("Curtidas: " + post.getCurtidas().size());
                System.out.println("Comentários: " + post.getComentarios().size());
            }
        }
    }
}
