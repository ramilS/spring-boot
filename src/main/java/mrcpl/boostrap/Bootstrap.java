package mrcpl.boostrap;

import mrcpl.domain.User;
import mrcpl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${baseUser.password:user}")
    String baseUsername;

    @Value("${baseUser.password:password}")
    String basePassword;


    public Bootstrap(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User(baseUsername, passwordEncoder.encode(basePassword)));
    }
}