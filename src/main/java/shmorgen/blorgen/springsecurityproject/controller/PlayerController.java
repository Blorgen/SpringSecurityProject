package shmorgen.blorgen.springsecurityproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shmorgen.blorgen.springsecurityproject.model.Player;
import shmorgen.blorgen.springsecurityproject.repository.PlayerRepository;

@Controller
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/actions")
    public String getActionsPage(Model model){
        model.addAttribute("unit", new Player());
        return "players/actions";
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("players", playerRepository.findAll());
        return "players/list";
    }

    @PreAuthorize("hasAuthority(('players:read'))")
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model){
        model.addAttribute("players", playerRepository.findById(id).orElse(new Player(-1l, "not found","not found")));
        return "players/list";
    }

    @PreAuthorize("hasAuthority(('players:write'))")
    @PostMapping
    public String create(@ModelAttribute Player unit, Model model){
        model.addAttribute("unit", new Player());
        playerRepository.save(unit);
        model.addAttribute("players", playerRepository.findAll());
        return "players/list";
    }

    @PreAuthorize("hasAuthority(('players:write'))")
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id, Model model){
        if (playerRepository.findById(id).isPresent()) {
            playerRepository.deleteById(id);
        }
        model.addAttribute("players", playerRepository.findAll());
        return "players/list";
    }
}
