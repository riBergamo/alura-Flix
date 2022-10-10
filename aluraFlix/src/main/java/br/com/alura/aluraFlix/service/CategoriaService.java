package br.com.alura.aluraFlix.service;


import br.com.alura.aluraFlix.dto.CategoriaDto;
import br.com.alura.aluraFlix.form.CategoriaForm;
import br.com.alura.aluraFlix.models.Categoria;
import br.com.alura.aluraFlix.models.Video;
import br.com.alura.aluraFlix.repository.CategoriaRepository;
import br.com.alura.aluraFlix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VideoRepository videoRepository;

    public Page<CategoriaDto> findall(String tituloCategoria,
                                      Pageable pageable) {

        if (tituloCategoria == null) {
            Page<Categoria> videos = categoriaRepository.findAll(pageable);
            return CategoriaDto.converter(videos);
        } else {
            Page<Categoria> videos = categoriaRepository.findByTitulo(tituloCategoria, pageable);
            return CategoriaDto.converter(videos);
        }
    }

    public ResponseEntity<CategoriaDto> findone(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(new CategoriaDto(categoria.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Page<Video>> videoByCategory(Long id,
                                                       Pageable pageable) {

        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()) {
            Page<Video> videos = videoRepository.getVideosByCategoriaId(categoria.get().getId(), pageable);
            return ResponseEntity.ok(videos);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<CategoriaDto> create(CategoriaForm categoriaForm, UriComponentsBuilder uriBuilder) {
        Categoria categoria = categoriaForm.converter(categoriaRepository);
        categoriaRepository.save(categoria);

        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));

    }

    public ResponseEntity<CategoriaDto> update(Long id, CategoriaForm categoriaForm) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria categoria = categoriaForm.update(id, categoriaRepository);
            return ResponseEntity.ok(new CategoriaDto(categoria));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity delete(Long id) {
        Optional<Categoria> optinal = categoriaRepository.findById(id);
        if (optinal.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok("Deletado com sucesso!");
        }
        return ResponseEntity.badRequest().body("O id não foi encontrado.");
    }




}
