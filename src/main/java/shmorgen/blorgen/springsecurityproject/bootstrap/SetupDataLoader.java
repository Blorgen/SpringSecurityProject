package shmorgen.blorgen.springsecurityproject.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shmorgen.blorgen.springsecurityproject.model.Player;
import shmorgen.blorgen.springsecurityproject.model.Role;
import shmorgen.blorgen.springsecurityproject.model.Status;
import shmorgen.blorgen.springsecurityproject.model.User;
import shmorgen.blorgen.springsecurityproject.repository.PlayerRepository;
import shmorgen.blorgen.springsecurityproject.repository.UserRepository;

@Component
public class SetupDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    public SetupDataLoader(UserRepository userRepository, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        int userCount = userRepository.findAll().size();
        int playerCount = playerRepository.findAll().size();
        if (userCount==0) {
            loadUsers();
        }

        if (playerCount==0) {
            loadPlayers();
        }
    }

    private void loadPlayers() {
        Player player1 = new Player();
        player1.setId(1l);
        player1.setFirstName("Ork");
        player1.setLastName("Pod");
        playerRepository.save(player1);

        Player player2 = new Player();
        player2.setId(2l);
        player2.setFirstName("Gnom");
        player2.setLastName("2D");
        playerRepository.save(player2);

        Player player3 = new Player();
        player3.setId(3l);
        player3.setFirstName("Silver");
        player3.setLastName("Name");
        playerRepository.save(player3);
    }

    private void loadUsers() {
        User user = new User();
        user.setEmail("user");
        user.setFirstName("User");
        user.setLastName("User");
        user.setRole(Role.ADMIN);
        user.setPassword("$2a$12$VcQ/IUUqHd164c8ta2e8NuolGO4yYYcnzGt6epaUth2EY5.n2/dP2");
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);

        User admin = new User();
        admin.setEmail("admin");
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setRole(Role.ADMIN);
        admin.setPassword("$2a$12$fzx58iXrrOfs.hECjOWqP.q.lG3H/EKpnGVanTUjCnT9TJ/mFvDKO");
        admin.setStatus(Status.ACTIVE);
        userRepository.save(admin);


    }
}
