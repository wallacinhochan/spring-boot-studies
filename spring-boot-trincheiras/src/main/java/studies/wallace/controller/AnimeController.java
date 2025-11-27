package studies.wallace.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import studies.wallace.domain.Anime;
import studies.wallace.mapper.AnimeMapper;
import studies.wallace.request.AnimePostRequest;
import studies.wallace.request.AnimePutRequest;
import studies.wallace.response.AnimeGetResponse;

import java.util.List;

@RestController
@RequestMapping("v1/animes")
public class AnimeController {
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;
    private static final String CONTENT_NOT_FIND_MENSAGE = "Content not found";

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAllAnime(@RequestParam(required = false) String name) {
        var animesList = Anime.getAnimes();
        var animesListGet = MAPPER.toAnimeGetResponseList(animesList);

        if (name == null) return ResponseEntity.ok(animesListGet);

        var filter = animesListGet
                .stream()
                .filter(animes -> animes.getName().equalsIgnoreCase(name))
                .toList();

        return ResponseEntity.ok(filter);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> getById(@PathVariable Long id) {
        var animeGetresponseList = MAPPER.toAnimeGetResponseList(Anime.getAnimes());
        var filter = animeGetresponseList
                .stream()
                .filter(animes -> animes.getId().equals(id)).
                findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CONTENT_NOT_FIND_MENSAGE));

        if (filter == null) return ResponseEntity.noContent().build();

        return ResponseEntity
                .ok(filter);
    }

    @PostMapping
    public ResponseEntity<AnimeGetResponse> saveAnime(@RequestBody AnimePostRequest animePostRequest) {

        if (animePostRequest == null) return null;

        var anime = MAPPER.toAnime(animePostRequest);
        var animeGetResponse = MAPPER.toAnimeGetResponse(anime);

        Anime.getAnimes().add(anime);

        return ResponseEntity.status(HttpStatus.CREATED).body(animeGetResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        var animeToDelete = Anime.getAnimes().stream()
                .filter(anime -> anime.getId().equals(id)).
                findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CONTENT_NOT_FIND_MENSAGE));

        Anime.getAnimes().remove(animeToDelete);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAnime(@RequestBody AnimePutRequest animePutRequest, @PathVariable Long id) {

        var animeToRemove = Anime.getAnimes().stream()
                .filter(anime -> anime.getId().equals(id)).
                findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CONTENT_NOT_FIND_MENSAGE));

        var animeToUpdate = MAPPER.toAnime(animePutRequest);
        Anime.getAnimes().remove(animeToRemove);
        Anime.getAnimes().add(animeToUpdate);

        return ResponseEntity.noContent().build();
    }

}
