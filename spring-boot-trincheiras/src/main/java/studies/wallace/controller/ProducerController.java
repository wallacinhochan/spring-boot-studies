package studies.wallace.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import studies.wallace.domain.Producer;
import studies.wallace.mapper.ProducerMapper;
import studies.wallace.request.ProducerPostRequest;
import studies.wallace.request.ProducerPutRequest;
import studies.wallace.response.ProducerGetResponse;

import java.util.List;

@RestController
@RequestMapping("v1/producer")
public class ProducerController {
    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAllProducer(@RequestParam(required = false) String name) {
        var producerList = Producer.getProducers();
        var producerGetResponsList = MAPPER.toProducerGetResponseList(producerList);

        if (name == null) return ResponseEntity.ok(producerGetResponsList);

        var filter = producerGetResponsList.stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();

        return ResponseEntity.ok(filter);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findByID(@PathVariable Long id) {
        var producers = Producer.getProducers();
        var produrcersGetResponse = MAPPER.toProducerGetResponseList(producers);
        var response = produrcersGetResponse.stream()
                .filter(producer -> producer.getId().equals(id)).
                findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
        if (response == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }

    //Eu consigo tambem colocar headers obrigatorios! com headers no post e consigo pegar os heders no
    // escopo da função!
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProducerGetResponse> saveProducer(@RequestBody ProducerPostRequest producerpostrequest) {
        if (producerpostrequest == null) return null;

        var producer = MAPPER.toProducer(producerpostrequest);

        Producer.getProducers().add(producer);

        var producerGetResponse = MAPPER.toProducerGetResponse(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(producerGetResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id) {
        var producerToDelete = Producer.getProducers().stream()
                .filter(producer -> producer.getId().equals(id)).
                findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));

        Producer.getProducers().remove(producerToDelete);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateByID(@PathVariable Long id, @RequestBody ProducerPutRequest producerputrequest) {
        var producerToDelete = Producer.getProducers().stream()
                .filter(producer -> producer.getId().equals(id)).
                findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));

        var producer = MAPPER.toProducer(producerputrequest, producerToDelete.getCreatedAt());
        Producer.getProducers().remove(producerToDelete);
        Producer.getProducers().add(producer);

        return ResponseEntity.noContent().build();
    }
}
