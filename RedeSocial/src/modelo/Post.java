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

    // Construtor
    public Post(Usuario autor, String conteudo) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = LocalDateTime.now();
        this.curtidas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    //meus get e set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    //metodos para adicionar e remover curtidas
    public void adicionarCurtida(Usuario usuario) {
        curtidas.add(usuario);
    }
    //metodo para remover
    public void removerCurtida(Usuario usuario) {
        curtidas.remove(usuario);
    }

    //metodo para adicionar um comentário
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
