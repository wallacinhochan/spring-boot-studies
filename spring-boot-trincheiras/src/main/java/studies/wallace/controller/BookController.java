package studies.wallace.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/books")
@Slf4j
public class BookController {
    @GetMapping
    public List<String> listAll() {
        log.info(Thread.currentThread().getName());
        return List.of("Lord of the rings", "Dom Casmurro", "Moreninha");
    }
}
