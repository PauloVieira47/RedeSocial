package com.redesocial.ui;

import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Usuario;
import java.util.Scanner;

public class MenuPrincipal {
    private final GerenciadorUsuarios gerenciadorUsuarios; // Gerenciador de usuários
    private final Scanner scanner; // Scanner para leitura de entrada do usuário

    public MenuPrincipal(GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.scanner = new Scanner(System.in);
    }

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

            }
        }
    }
}
