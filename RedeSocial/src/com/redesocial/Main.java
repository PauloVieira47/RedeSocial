package com.redesocial;

import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.gerenciador.GerenciadorPosts;
import com.redesocial.ui.MenuPrincipal;
import com.redesocial.modelo.Usuario;

public class Main {
    public static void main(String[] args) {
        //inicializa o Gerenciador de usuarios e o Gerenciador de posts
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();

        //criando o Menu Principal e passando os gerenciadores
        MenuPrincipal menuPrincipal = new MenuPrincipal(gerenciadorUsuarios);

        //Motrar o meu Menu Principal
        menuPrincipal.exibirMenu();
    }
}
