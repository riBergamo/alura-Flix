package br.com.alura.aluraFlix.controller;

import br.com.alura.aluraFlix.dto.CategoriaDto;
import br.com.alura.aluraFlix.form.CategoriaForm;
import br.com.alura.aluraFlix.models.Video;
import br.com.alura.aluraFlix.service.CategoriaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    private CategoriaService categoriaService;

    @GetMapping
    public Page<CategoriaDto> findall(@RequestParam(required = false) String tituloCategoria,
                                      @PageableDefault(sort = "id", page = 0, size = 5) Pageable pageable) {

        return categoriaService.findall(tituloCategoria, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findone(@PathVariable Long id) {

        return categoriaService.findone(id);
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<Page<Video>> videoByCategory(@PathVariable Long id,
                                                       @PageableDefault(sort = "id", page = 0, size = 10) Pageable pageable) {

        return categoriaService.videoByCategory(id, pageable);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> create(@RequestBody @Valid CategoriaForm categoriaForm,
                                               UriComponentsBuilder uriBuilder) {

        return categoriaService.create(categoriaForm, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> update(@PathVariable Long id,
                                               @RequestBody @Valid CategoriaForm categoriaForm) {

        return categoriaService.update(id, categoriaForm);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {

        return categoriaService.delete(id);
    }



}
