package br.com.alura.aluraFlix.form;

import br.com.alura.aluraFlix.models.Categoria;
import br.com.alura.aluraFlix.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank @Length(max = 70)
    private String titulo;
    @NotBlank
    private String cor;

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

    public Categoria converter(CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.findByTitulo(titulo);
        return new Categoria(titulo, cor);
    }

    public Categoria update(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getReferenceById(id);//att
        categoria.setTitulo(this.titulo);
        categoria.setCor(this.cor);

        return categoria;
    }
}
