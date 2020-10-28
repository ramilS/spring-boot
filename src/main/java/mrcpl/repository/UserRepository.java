package mrcpl.repository;

import io.vavr.control.Option;
import mrcpl.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Transactional(readOnly = true)
    Option<User> findByUsername(String username);
}
