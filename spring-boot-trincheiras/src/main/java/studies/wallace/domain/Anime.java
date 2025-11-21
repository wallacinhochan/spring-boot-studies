package studies.wallace.domain;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class Anime {
    private Long id;
    private String name;
    @Getter
    private static List<Anime> animes = new ArrayList<>();

    static {
        var dragonBall = new Anime(1L, "Dragon Ball");
        var onePiece = new Anime(2L, "One Piece");
        var fullMetal = new Anime(3L, "Full Metal");
        animes.addAll(List.of(dragonBall, onePiece, fullMetal));
    }


    public Anime(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public static void setAnimes(List<Anime> animes) {
        Anime.animes = animes;
    }

}
