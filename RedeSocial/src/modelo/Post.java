package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private Integer id;
    private Usuario autor;
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private List<Usuario> curtidas;
    private List<Comentario> comentarios;

    // Construtores
    public Post(Usuario autor, String conteudo) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = LocalDateTime.now();
        this.curtidas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    //Metodos pra adicionar curtidas
    public void adicionarCurtida(Usuario usuario) {
        curtidas.add(usuario);
    }
    //Para remover
    public void removerCurtida(Usuario usuario) {
        curtidas.remove(usuario);
    }
    //Metodos pra adicionar comentario
    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    @Override
    public String toString() {
        return "Post{" +
                "autor=" + autor.getUsername() +
                ", conteudo='" + conteudo + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                '}';
    }
}
