package ms.cinemas.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.cinemas.dao.CinemaRepository;
import ms.cinemas.model.Cinema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/webapi")
@RequiredArgsConstructor
public class CinemaRest {

    private final CinemaRepository cinemaRepository;


    private String movieServiceUrl;
    @GetMapping("/cinemas")
    public List<Cinema> getCinemas() {
        log.info("about to retrieve all cinemas");
        return cinemaRepository.findAll();
    }

    @GetMapping("/movies/{id}/cinemas")
    public List<Cinema> getCinemasByMovie(@PathVariable int id) {
        log.info("about to retrieve cinemas by movie {}", id);
        return cinemaRepository.findCinemasByMoviesIsContaining(id);
    }
    private void fillMovieNames(Cinema cinema) {
        cinema.getMovies().forEach(movieId -> {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<MovieDTO> responseEntity = restTemplate.exchange(
                    movieServiceUrl
            );
        });
    }
}
