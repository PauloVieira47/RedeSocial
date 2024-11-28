package com.redesocial.ui;

import com.redesocial.modelo.Usuario;
import com.redesocial.modelo.Post;
import com.redesocial.modelo.Comentario;
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
            System.out.println("6. Editar Perfil"); // Adicionando a opção de Editar Perfil
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine()); // Usando nextLine() para pegar a entrada como String
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> criarPost();
                case 2 -> verPerfil();
                case 3 -> buscarUsuarios();
                case 4 -> gerenciarAmizades();
                case 5 -> verFeedNoticias();
                case 6 -> editarPerfil(); // Chama a função de editar perfil
                case 0 -> System.out.println("Fazendo logout...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void editarPerfil() {
        System.out.println("=== Editar Perfil ===");
        System.out.println("Escolha o campo que deseja editar:");
        System.out.println("1. Nome");
        System.out.println("2. Username");
        System.out.println("3. Email");
        System.out.println("4. Senha");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha restante

        switch (opcao) {
            case 1 -> editarNome();
            case 2 -> editarUsername();
            case 3 -> editarEmail();
            case 4 -> editarSenha();
            case 0 -> System.out.println("Voltando ao menu...");
            default -> System.out.println("Opção inválida.");
        }
    }

    // Função para editar o nome
    private void editarNome() {
        System.out.print("Digite o novo nome: ");
        String novoNome = scanner.nextLine();
        if (novoNome.trim().isEmpty()) {
            System.out.println("O nome não pode ser vazio.");
        } else if (novoNome.equals(usuario.getNome())) {
            System.out.println("O nome não pode ser igual ao anterior.");
        } else {
            usuario.setNome(novoNome);
            System.out.println("Nome atualizado com sucesso!");
        }
    }

    // Função para editar o username
    private void editarUsername() {
        System.out.print("Digite o novo username: ");
        String novoUsername = scanner.nextLine();
        if (novoUsername.equals(usuario.getUsername())) {
            System.out.println("O username não pode ser igual ao anterior.");
        } else {
            usuario.setUsername(novoUsername);
            System.out.println("Username atualizado com sucesso!");
        }
    }

    // Função para editar o email
    private void editarEmail() {
        String novoEmail;
        while (true) {
            System.out.print("Digite o novo email: ");
            novoEmail = scanner.nextLine();

            if (novoEmail.equals(usuario.getEmail())) {
                System.out.println("O email não pode ser igual ao anterior.");
            } else if (!novoEmail.contains("@")) {
                System.out.println("Email inválido. O email deve conter '@'. Tente novamente.");
            } else {
                usuario.setEmail(novoEmail);
                System.out.println("Email atualizado com sucesso!");
                break;
            }
        }
    }

    // Função para editar a senha
    private void editarSenha() {
        System.out.print("Digite a nova senha: ");
        String novaSenha = scanner.nextLine();

        if (novaSenha.equals(usuario.getSenha())) {
            System.out.println("A nova senha não pode ser igual à senha anterior.");
        } else if (novaSenha.length() < 6) {
            System.out.println("A senha deve ter no mínimo 6 caracteres.");
        } else {
            usuario.setSenha(novaSenha);
            System.out.println("Senha atualizada com sucesso!");
        }

}

    private void criarPost() {
        System.out.println("=== Criar Post ===");
        System.out.print("Digite o conteúdo do seu post: ");
        String conteudo = scanner.nextLine();

        try {
            Post post = new Post(usuario, conteudo);
            gerenciadorPosts.cadastrar(post);
            System.out.println("Post criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar post: " + e.getMessage());
        }
    }

    private void verPerfil() {
        System.out.println("=== Meu Perfil ===");
        // Exibe as informações básicas do perfil
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Username: " + usuario.getUsername());
        System.out.println("Email: " + usuario.getEmail());

        // Exibe a lista de amigos
        System.out.println("\n=== Meus Amigos ===");
        List<Usuario> amigos = usuario.getAmigos();
        if (amigos.isEmpty()) {
            System.out.println("Você não tem amigos ainda.");
        } else {
            for (Usuario amigo : amigos) {
                System.out.println("- " + amigo.getNome() + " (@" + amigo.getUsername() + ")");
            }
        }

        // Exibe os posts do usuário
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

            System.out.print("Gostaria de interagir com algum desses usuários? Digite o nome ou username ou 0 para voltar: ");
            String escolha = scanner.nextLine();
            if (!escolha.equals("0")) {
                Usuario usuarioEscolhido = gerenciadorUsuarios.buscarPorUsername(escolha);
                if (usuarioEscolhido != null) {
                    System.out.println("Você escolheu: " + usuarioEscolhido.getNome());
                } else {
                    System.out.println("Usuário não encontrado.");
                }
            }
        }
    }

    private void gerenciarAmizades() {
        int opcao = -1;
        do {
            System.out.println("\n1. Ver Amigos");
            System.out.println("2. Adicionar Amigo");
            System.out.println("3. Remover Amigo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
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
            System.out.println("Você ainda não tem amigos. Tente adicionar amigos para começar a interagir!");
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

        // Obter o feed (posts do usuário logado e dos amigos)
        List<Post> feed = gerenciadorPosts.listarPorUsuario(usuario.getId());
        for (Usuario amigo : usuario.getAmigos()) {
            feed.addAll(gerenciadorPosts.listarPorUsuario(amigo.getId()));
        }

        // Ordenar os posts do feed por data de publicação (mais recente primeiro)
        feed.sort((p1, p2) -> p2.getDataPublicacao().compareTo(p1.getDataPublicacao()));

        if (feed.isEmpty()) {
            System.out.println("Nenhuma postagem no feed.");
        } else {
            for (Post post : feed) {
                System.out.println("\n=== Post ===");
                System.out.println("Autor: " + post.getAutor().getNome());
                System.out.println("Data: " + post.getDataPublicacao());
                System.out.println("Conteúdo: " + post.getConteudo());
                System.out.println("Curtidas: " + post.getCurtidas().size());
                System.out.println("Comentários: " + post.getComentarios().size());

                // Opções de interação
                System.out.print("Deseja interagir com este post? \n(1 - Curtir, 2 - Comentar, 3 - Remover Curtida, 4 - Remover Comentário, 0 - Ignorar): ");
                int acao = scanner.nextInt();
                scanner.nextLine(); // Consumir a linha restante

                switch (acao) {
                    case 1 -> curtirPost(post);
                    case 2 -> comentarPost(post);
                    case 3 -> removerCurtida(post);
                    case 4 -> removerComentario(post);
                    case 0 -> System.out.println("Ignorando post...");
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }

    // Método para curtir um post
    private void curtirPost(Post post) {
        if (!post.getCurtidas().contains(usuario)) {
            post.adicionarCurtida(usuario);
            System.out.println("Você curtiu o post!");
        } else {
            System.out.println("Você já curtiu este post.");
        }
    }

    // Método para remover curtida de um post
    private void removerCurtida(Post post) {
        if (post.getCurtidas().contains(usuario)) {
            post.removerCurtida(usuario);
            System.out.println("Você removeu sua curtida.");
        } else {
            System.out.println("Você ainda não curtiu este post.");
        }
    }

    // Método para comentar em um post
    private void comentarPost(Post post) {
        System.out.print("Digite seu comentário: ");
        String conteudo = scanner.nextLine();
        Comentario comentario = new Comentario(usuario, conteudo, post);
        post.adicionarComentario(comentario);
        System.out.println("Comentário adicionado!");
    }

    // Método para remover um comentário
    private void removerComentario(Post post) {
        List<Comentario> comentarios = post.getComentarios();

        if (comentarios.isEmpty()) {
            System.out.println("Este post não possui comentários.");
            return;
        }

        System.out.println("Seus comentários:");
        for (int i = 0; i < comentarios.size(); i++) {
            Comentario comentario = comentarios.get(i);
            if (comentario.getAutor().equals(usuario)) {
                System.out.println(i + 1 + ". " + comentario.getConteudo());
            }
        }

        System.out.print("Escolha o número do comentário que deseja remover ou 0 para cancelar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (opcao > 0 && opcao <= comentarios.size()) {
            Comentario comentarioARemover = comentarios.get(opcao - 1);
            if (comentarioARemover.getAutor().equals(usuario)) {
                post.getComentarios().remove(comentarioARemover);
                System.out.println("Comentário removido com sucesso.");
            } else {
                System.out.println("Você só pode remover seus próprios comentários.");
            }
        } else if (opcao == 0) {
            System.out.println("Cancelado.");
        } else {
            System.out.println("Opção inválida.");
        }
    }
}
