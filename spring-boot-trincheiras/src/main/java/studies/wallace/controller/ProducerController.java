package studies.wallace.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.wallace.domain.Producer;
import studies.wallace.request.ProducerPostRequest;
import studies.wallace.response.ProducerGetResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("v1/producer")
public class ProducerController {
    @GetMapping
    public List<Producer> listAllProducer(@RequestParam(required = false) String name) {
        var producerList = Producer.getProducers();
        if (name == null) return producerList;
        return producerList.stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();
    }

    @GetMapping("{id}")
    public Producer listAllProducer(@PathVariable Long id) {

        return Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId().equals(id)).
                findFirst()
                .orElse(null);
    }

    //Eu consigo tambem colocar headers obrigatorios! com headers no post e consigo pegar os heders no
    // escopo da função!
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProducerGetResponse> saveProducer(@RequestBody ProducerPostRequest producerpostrequest) {
        if (producerpostrequest == null) return null;
        var prodecer = Producer
                .builder()
                .id(ThreadLocalRandom.current().nextLong(100_000))
                .name(producerpostrequest.getName())
                .createdAt(LocalDateTime.now())
                .build();

        Producer.getProducers().add(prodecer);

        var producerGetResponse = ProducerGetResponse
                .builder()
                .id(prodecer.getId()).name(prodecer.getName())
                .createdAt(prodecer.getCreatedAt())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(producerGetResponse);
    }
}
