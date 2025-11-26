package studies.wallace.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime {
    @EqualsAndHashCode.Include
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
