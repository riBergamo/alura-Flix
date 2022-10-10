package br.com.alura.aluraFlix.repository;

import br.com.alura.aluraFlix.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Page<Video> findByTitulo(String tituloVideo, Pageable paginacao);

    Video findByTitulo(String titulo);

    @Query(value = "SELECT * FROM video v WHERE v.categoria_id = ?1",
            countQuery = "SELECT count(*) FROM video v WHERE v.categoria_id = ?1",
            nativeQuery = true)
    Page<Video> getVideosByCategoriaId(Long categoria_id, Pageable pageable);

}
