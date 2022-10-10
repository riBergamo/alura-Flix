package br.com.alura.aluraFlix.controller;

import br.com.alura.aluraFlix.models.Video;
import br.com.alura.aluraFlix.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class VideoControllerTest {

    @MockBean
    private VideoRepository videoRepository;

    @Autowired
    private MockMvc mockMvc;

    //
    @Test
    public void deveriaListarTodosOsVideos() {

    }


    public void deveriaBuscarVideoPorTitulo() {

    }

    public void deveriaBuscarVideoPorId() {

    }

    public void deveriaCriarNovoVideo() {

    }

    public void deveriaAtualizarVideoExistente() {

    }

    public void deveriaDeletarVideoExistente() {

    }

}
