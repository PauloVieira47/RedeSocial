package com.redesocial.ui;
import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Usuario;

import java.util.Scanner;
public class MenuPrincipal {
    private final GerenciadorUsuarios gerenciadorUsuarios;
    private final Scanner scanner;

    public MenuPrincipal(GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.scanner = new Scanner(System.in);
    }
     //mostrar o menu principal para o usuario
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Fazer Login");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> fazerLogin();
                case 2 -> cadastrarUsuario();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
     // função que permite que o usuário faça login.

    private void fazerLogin() {
        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();

        Usuario usuario = gerenciadorUsuarios.buscarPorUsername(username);
        if (usuario != null) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + usuario.getNome());
            exibirMenuLogado(usuario);
        } else {
            System.out.println("Usuário não encontrado. Verifique o username ou cadastre-se.");
        }
    }
     //cadastra um novo usuário no sistema
    private void cadastrarUsuario() {
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, username, email, senha);
        gerenciadorUsuarios.cadastrar(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }
    private void exibirMenuLogado(Usuario usuario) {
        MenuUsuario menuUsuario = new MenuUsuario(usuario);
        menuUsuario.exibirMenu();
    }
}
