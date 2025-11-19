package studies.wallace.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/characters")
public class CharacterController {
    private static final List<String> CHARACTERS = List.of("Sam", "Frodo", "Bilbo");


    @GetMapping
    public List<String> getAllCharacters() {
        return CHARACTERS;
    }

    @GetMapping("filter")
    public List<String> getAllCharactersParam(@RequestParam(defaultValue = "") String name) {
        return CHARACTERS.stream().filter(characters -> characters.equalsIgnoreCase(name)).toList();
    }

    @GetMapping("filterList")
    public List<String> getAllCharactersParamList(@RequestParam(defaultValue = "") List<String> names) {
        return CHARACTERS.stream().filter(names::contains).toList();
    }

    @GetMapping("{name}")
    public String findByName(@PathVariable String name) {
        return CHARACTERS
                .stream()
                .filter(characters -> characters.equalsIgnoreCase(name))
                .findFirst().orElse("");
    }

}
