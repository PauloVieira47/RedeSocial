package com.redesocial.ui;

import com.redesocial.modelo.Usuario;

import java.util.Scanner;

public class MenuUsuario {
    private final Usuario usuario;
    private final Scanner scanner;
    //construtor do menu
    public MenuUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.scanner = new Scanner(System.in);
    }
    //Exibe o menu para o usuário
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DO USUÁRIO ===");
            System.out.println("1. Criar Post");
            System.out.println("2. Ver Meu Perfil");
            System.out.println("3. Buscar Usuários");
            System.out.println("4. Gerenciar Amizades");
            System.out.println("5. Ver Feed de Notícias");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // quebra de linha

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
    //metodo para criar novo post
    private void criarPost() {
        System.out.println("=== Criar Post ===");
        System.out.print("Digite o conteúdo do seu post: ");
        String conteudo = scanner.nextLine();
        System.out.println("Post criado: " + conteudo);
    }

    //Motrar no perfil do usuario
    private void verPerfil() {
        System.out.println("=== Meu Perfil ===");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Username: " + usuario.getUsername());
        System.out.println("Email: " + usuario.getEmail());
        // Exibe mais informações quando conectarmos ao Gerenciador
    }
    //busca por outros usuarios
    private void buscarUsuarios() {
        System.out.println("= Buscar Usuários =");
        System.out.println("Função a ser implementada...");
    }
    //gerenciador de amizades para o usuario.
    private void gerenciarAmizades() {
        System.out.println("= Gerenciar Amizades =");
        System.out.println("Função a ser implementada...");
    }
    // mostrar as  notícias do usuário
    private void verFeedNoticias() {
        System.out.println("= Feed de Notícias =");
        System.out.println("Função a ser implementada...");
    }
}

