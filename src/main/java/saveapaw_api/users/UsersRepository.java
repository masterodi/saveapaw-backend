package saveapaw_api.users;

import org.springframework.stereotype.Repository;

import saveapaw_api.shared.CrudRepository;

@Repository
public interface UsersRepository extends CrudRepository<User, String> {
}
