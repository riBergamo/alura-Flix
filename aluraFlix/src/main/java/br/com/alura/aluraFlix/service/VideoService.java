package br.com.alura.aluraFlix.service;

import br.com.alura.aluraFlix.dto.VideoDto;
import br.com.alura.aluraFlix.form.VideoForm;
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
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<VideoDto> findall(String titulo, Pageable pageable) {

        if (titulo == null) {
            Page<Video> videos = videoRepository.findAll(pageable);
            return VideoDto.converter(videos);
        } else {
            Page<Video> videos = videoRepository.findByTitulo(titulo, pageable);
            return VideoDto.converter(videos);
        }

    }

    public ResponseEntity<VideoDto> findone(Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            return ResponseEntity.ok(new VideoDto(video.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<VideoDto> create(VideoForm videoForm, UriComponentsBuilder uriBuilder) {
        Video video = videoForm.converter(videoRepository);
        Optional<Categoria> categoria = categoriaRepository.findById(videoForm.getCategoriaId());
        video.setCategoria(categoria.get());
        videoRepository.save(video);

        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));

    }

    public ResponseEntity<VideoDto> update(Long id, VideoForm videoForm) {
        Optional<Video> optional = videoRepository.findById(id);
        if (optional.isPresent()) {
            Video video = videoForm.update(id, videoRepository);
            return ResponseEntity.ok(new VideoDto(video));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity delete(Long id) {
        Optional<Video> optional = videoRepository.findById(id);
        if (optional.isPresent()) {
            videoRepository.deleteById(id);
            return ResponseEntity.ok("Deletado com sucesso!");
        }
        return ResponseEntity.badRequest().body("O id não foi encontrado.");
    }

}
