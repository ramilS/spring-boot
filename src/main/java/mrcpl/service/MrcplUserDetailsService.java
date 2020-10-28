package mrcpl.service;

import mrcpl.domain.User;
import mrcpl.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MrcplUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public MrcplUserDetailsService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(User::toSpringUser)
                .getOrElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
