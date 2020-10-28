package mrcpl.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Collections;

@Entity
@Table(name = "base_user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public org.springframework.security.core.userdetails.User toSpringUser() {
        return new org.springframework.security.core.userdetails.User(
                this.username,
                this.password,
                Collections.singletonList(new SimpleGrantedAuthority("user")));
    }
}
