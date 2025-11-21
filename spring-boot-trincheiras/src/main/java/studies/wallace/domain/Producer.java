package studies.wallace.domain;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class Producer {
    private Long id;
    private String name;
    @Getter
    private static List<Producer> producers = new ArrayList<>();

    static {
        var mappa = new Producer(1L, "Mappa");
        var empresa2 = new Producer(2L, "Empresa2");
        var empresa3 = new Producer(3L, "Empressa3");
        producers.addAll(List.of(mappa, empresa2, empresa3));
    }


    public Producer(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public static void setAnimes(List<Producer> producer) {
        Producer.producers = producer;
    }

}
