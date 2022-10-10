package br.com.alura.aluraFlix.repository;

import br.com.alura.aluraFlix.models.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Page<Categoria> findByTitulo(String tituloCategoria, Pageable pageable);

    Categoria findByTitulo(String tituloCategoria);


}
