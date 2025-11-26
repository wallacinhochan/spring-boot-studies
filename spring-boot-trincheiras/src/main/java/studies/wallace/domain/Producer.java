package studies.wallace.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producer {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    @Getter
    private static List<Producer> producers = new ArrayList<>();

    static {
        var mappa = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).build();
        var empresa2 = Producer.builder().id(2L).name("Empresa 2").createdAt(LocalDateTime.now()).build();
        var empresa3 = Producer.builder().id(3L).name("Empresa 3").createdAt(LocalDateTime.now()).build();
        producers.addAll(List.of(mappa, empresa2, empresa3));
    }
}
