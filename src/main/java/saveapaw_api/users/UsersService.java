package saveapaw_api.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import saveapaw_api.users.exceptions.UserConflictException;
import saveapaw_api.users.exceptions.UserNotFoundException;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserMapper mapper;

    public List<UserDTO.Query> getMany() {
        var res = usersRepository.findAll();
        return res.stream().map((user) -> mapper.toUserDTO(user)).toList();
    }

    public UserDTO.Query getOne(String id) throws UserNotFoundException {
        var res = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return mapper.toUserDTO(res);
    }

    public UserDTO.Query create(UserDTO.Create dto) throws UserConflictException {
        try {
            var user = mapper.fromUserCreateDTO(dto);
            var res = usersRepository.save(user);
            return mapper.toUserDTO(res);
        } catch (DataIntegrityViolationException e) {
            if (UserConflictException.isEmailConflict(e)) {
                throw new UserConflictException.Email();
            }
            if (UserConflictException.isUsernameConflict(e)) {
                throw new UserConflictException.Username();
            }

            throw e;
        }

    }

    public List<UserDTO.Query> createMany(List<UserDTO.Create> dtos) {
        var users = dtos.stream().map((dto) -> mapper.fromUserCreateDTO(dto)).toList();
        var res = usersRepository.saveAll(users);
        return res.stream().map((user) -> mapper.toUserDTO(user)).toList();
    }

    public UserDTO.Query update(String id, UserDTO.Update dto) throws UserNotFoundException, UserConflictException {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        mapper.updateUser(dto, user);
        try {
            var res = usersRepository.save(user);
            return mapper.toUserDTO(res);
        } catch (DataIntegrityViolationException e) {
            if (UserConflictException.isEmailConflict(e)) {
                throw new UserConflictException.Email();
            }
            if (UserConflictException.isUsernameConflict(e)) {
                throw new UserConflictException.Username();
            }

            throw e;
        }

    }

    public void deleteAll() {
        usersRepository.deleteAll();
    }

    public void deleteById(String id) {
        usersRepository.deleteById(id);
    }

    public void deleteManyById(List<String> id) {
        usersRepository.deleteAllById(id);
    }
}
