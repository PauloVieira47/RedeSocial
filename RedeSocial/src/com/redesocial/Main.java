package com.redesocial;

import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.gerenciador.GerenciadorPosts;
import com.redesocial.ui.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        // Inicializando os gerenciadores de usuários e posts
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();

        // Criando o MenuPrincipal e passando os gerenciadores
        MenuPrincipal menuPrincipal = new MenuPrincipal(gerenciadorUsuarios, gerenciadorPosts);

        // Mostrar o menu principal
        menuPrincipal.exibirMenu();
    }
}
