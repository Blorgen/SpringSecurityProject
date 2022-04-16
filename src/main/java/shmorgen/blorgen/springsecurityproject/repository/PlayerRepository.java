package shmorgen.blorgen.springsecurityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shmorgen.blorgen.springsecurityproject.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
