package studies.wallace.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequestMapping("v1/greetings")
public class HelloController {

    @GetMapping("hi")
    public String hi() {
        return "Zap zap";
    }

    @PostMapping
    public Long save(@RequestBody String name) {
        if (log.isInfoEnabled()) {
            log.info("saving '{}'" + name);
        }
        return ThreadLocalRandom.current().nextLong(1, 1000);
    }

    @GetMapping("gaby")
    public String helloGaby() {
        return "Hello Gaby";
    }
}

