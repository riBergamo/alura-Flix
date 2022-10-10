package br.com.alura.aluraFlix.dto;

import br.com.alura.aluraFlix.models.Video;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;

//dados que saem da api para o cliente
public class VideoDto {

    @NotBlank
    private Long id;//devia ter id no dto?

    @NotBlank
    private String titulo;

    @NotBlank
    private String descrição;

    @NotBlank
    private String url;

    private String categoriaId;//Long

    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descrição = video.getDescricao();
        this.url = video.getUrl();
        this.categoriaId = video.getCategoria().getId().toString();//att
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescrição() {
        return descrição;
    }

    public String getUrl() {
        return url;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public static Page<VideoDto> converter(Page<Video> video) {
        return video.map(VideoDto::new);
    }
}