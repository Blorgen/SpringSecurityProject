package shmorgen.blorgen.springsecurityproject.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shmorgen.blorgen.springsecurityproject.model.Role;
import shmorgen.blorgen.springsecurityproject.model.Status;
import shmorgen.blorgen.springsecurityproject.model.User;
import shmorgen.blorgen.springsecurityproject.repository.UserRepository;

@Component
public class SetupDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public SetupDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = userRepository.findAll().size();
        if (count==0) {
            loadData();
        }

    }

    private void loadData() {
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
