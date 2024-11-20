
public class Main {

    public static void main(String[] args) {
        // Inicializando os gerenciadores
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();


        // Exibindo o menu principal para o usuário
        System.out.println("Bem-vindo à Rede Social");
        menuPrincipal.exibirMenu();
    }
}

