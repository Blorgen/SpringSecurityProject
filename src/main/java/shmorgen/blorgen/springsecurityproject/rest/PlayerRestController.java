package shmorgen.blorgen.springsecurityproject.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shmorgen.blorgen.springsecurityproject.model.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    private final List<Player> Player = Stream.of(
            new Player(1L, "Ivan", "Ivanov"),
            new Player(2L, "Semen", "Semenov"),
            new Player(3L, "Petr", "Petrov")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Player> getAll(){
        return Player;
    }

    @PreAuthorize("hasAuthority(('players:read'))")
    @GetMapping("/{id}")
    public Player getById(@PathVariable Long id){
        return Player.stream().filter(players -> players.getId().equals(id))
                .findFirst().orElse(null);
    }

    @PreAuthorize("hasAuthority(('players:write'))")
    @PostMapping
    public Player create(@RequestBody Player player){
        this.Player.add(player);
        return player;
    }

    @PreAuthorize("hasAuthority(('players:write'))")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.Player.removeIf(player -> player.getId().equals(id));
    }
}
