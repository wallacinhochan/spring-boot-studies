package studies.wallace.controller;

import org.springframework.web.bind.annotation.*;
import studies.wallace.domain.Anime;
import studies.wallace.mapper.AnimeMapper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("v1/animes")
public class AnimeController {
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping
    public List<Anime> listAllAnime(@RequestParam(required = false) String name) {
        var animesList = Anime.getAnimes();
        MAPPER.toAnimeGetResponseList(animesList);
        if (name == null) return animesList;
        return animesList.stream().filter(animes -> animes.getName().equalsIgnoreCase(name)).toList();
    }
    @GetMapping("{id}")
    public Anime listAllAnime(@PathVariable Long id) {

        return Anime.getAnimes()
                .stream()
                .filter(animes -> animes.getId().equals(id)).
                findFirst()
                .orElse(null);
    }

    @PostMapping
    public Anime saveAnime(@RequestBody Anime anime){
        if (anime == null) return null;
        anime.setId(ThreadLocalRandom.current().nextLong(10000, 99999));
        Anime.getAnimes().add(anime);
        return anime;
    }

}
