package modelo;

import java.time.LocalDateTime;

public class Comentario {
    //Contém informações sobre o autor, conteúdo e a data do comentário.
    private Integer id;
    private Usuario autor;
    private String conteudo;
    private LocalDateTime dataComentario;
    private Post post; //Post em que o comentário foi feito

    //Construtor do Comentario.
    public Comentario(Usuario autor, String conteudo, Post post) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataComentario = LocalDateTime.now();
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "autor=" + autor.getUsername() +
                ", conteudo='" + conteudo + '\'' +
                ", dataComentario=" + dataComentario +
                '}';
    }
}
