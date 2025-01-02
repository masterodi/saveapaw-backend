package saveapaw_api.users;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import saveapaw_api.shared.CrudRepository;

@Repository
public interface UsersRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}
