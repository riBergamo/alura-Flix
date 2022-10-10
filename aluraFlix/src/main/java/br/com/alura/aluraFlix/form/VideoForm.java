package br.com.alura.aluraFlix.form;

import br.com.alura.aluraFlix.models.Video;
import br.com.alura.aluraFlix.repository.VideoRepository;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

//dados que chegam do cliente para api(post - put)
public class VideoForm {

    @NotBlank @Length(max = 70)
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank
    private String url;
    @NotNull
    private Long categoriaId;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Video converter(VideoRepository videoRepository) {
        Video video = videoRepository.findByTitulo(titulo);
        return new Video(titulo, descricao, url);
    }

    public Video update(Long id, VideoRepository videoRepository) {
        Video video = videoRepository.getReferenceById(id);
        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);
        video.setUrl(this.url);

        return video;
    }
}
