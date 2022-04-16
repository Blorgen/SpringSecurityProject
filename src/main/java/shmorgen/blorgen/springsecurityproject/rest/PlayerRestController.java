package shmorgen.blorgen.springsecurityproject.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shmorgen.blorgen.springsecurityproject.model.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/api/players")
public class PlayerRestController {

    private final List<Player> players = Stream.of(
            new Player(1L, "Ivan", "Ivanov"),
            new Player(2L, "Semen", "Semenov"),
            new Player(3L, "Petr", "Petrov")
    ).collect(Collectors.toList());

    @GetMapping("/actions")
    public String getActionsPage(Model model){
        model.addAttribute("unit", new Player());
        return "players/actions";
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("players", players);
        return "players/list";
    }

    @PreAuthorize("hasAuthority(('players:read'))")
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model){
        model.addAttribute("players", players.stream().filter(players -> players.getId().equals(id))
                .findFirst().orElse(null));
        return "players/list";
    }

    @PreAuthorize("hasAuthority(('players:write'))")
    @PostMapping
    public String create(@ModelAttribute Player unit, Model model){
        model.addAttribute("unit", new Player());
        this.players.add(unit);
        model.addAttribute("players", players);
        return "players/list";
    }

    @PreAuthorize("hasAuthority(('players:write'))")
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id, Model model){
        model.addAttribute("players", players);
        this.players.removeIf(player -> player.getId().equals(id));
        return "players/list";
    }
}
