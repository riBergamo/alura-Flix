package br.com.alura.aluraFlix.dto;

import br.com.alura.aluraFlix.models.Categoria;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class  CategoriaDto {

    @NotBlank
    private Long id;

    @NotBlank @Length(max = 70)
    private String titulo;

    @NotBlank
    private String cor;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public static Page<CategoriaDto> converter(Page<Categoria> categorias) {
        return categorias.map(CategoriaDto::new);
    }
}
