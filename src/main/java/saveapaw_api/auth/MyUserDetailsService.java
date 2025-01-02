package saveapaw_api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import saveapaw_api.users.UsersRepository;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        var userDetails = User.builder().username(user.getUsername()).password(user.getPassword())
                .roles(user.getRole().toString()).build();

        return userDetails;
    }

}
