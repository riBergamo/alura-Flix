package br.com.alura.aluraFlix.controller;

import br.com.alura.aluraFlix.dto.VideoDto;
import br.com.alura.aluraFlix.form.VideoForm;
import br.com.alura.aluraFlix.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideoService videoService;

    @GetMapping
    //@ResponseBody
    public Page<VideoDto> findall(@RequestParam(required = false) String titulo,
                                @PageableDefault(sort = "id", page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {

        return videoService.findall(titulo, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> findone(@PathVariable Long id) {

        return videoService.findone(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VideoDto> create(@RequestBody @Valid VideoForm videoForm,
                                           UriComponentsBuilder uriBuilder) {

        return videoService.create(videoForm, uriBuilder);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> update(@PathVariable Long id,
                                           @RequestBody @Valid VideoForm videoForm) {
        return videoService.update(id, videoForm);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {

        return videoService.delete(id);
    }




}
