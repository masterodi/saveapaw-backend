package saveapaw_api.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserDTO.Query getOne(String id) {
        var res = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("User with id " + id + " not found"));
        return mapper.toUserDTO(res);
    }

    public UserDTO.Query create(UserDTO.Create dto) {
        var user = mapper.fromUserCreateDTO(dto);
        var res = usersRepository.save(user);
        return mapper.toUserDTO(res);
    }

    public List<UserDTO.Query> createMany(List<UserDTO.Create> dtos) {
        var users = dtos.stream().map((dto) -> mapper.fromUserCreateDTO(dto)).toList();
        var res = usersRepository.saveAll(users);
        return res.stream().map((user) -> mapper.toUserDTO(user)).toList();
    }

    public UserDTO.Query update(String id, UserDTO.Update dto) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("User with id " + id + " not found"));
        mapper.updateUser(dto, user);
        var res = usersRepository.save(user);
        return mapper.toUserDTO(res);
    }

    // public List<UserDTO> updateMany(List<UserUpdateManyDTO> dtos) {
    // List<String> ids = dtos.stream().map((dto) -> dto.id()).toList();
    // var users = usersRepository.findAllById(ids);
    // users.forEach((user) -> {
    // var dto = dtos.stream().filter(x -> user.getId().equals(x.id())).findFirst();
    // if (dto.isPresent()) {
    // mapper.updateUser(dto.get(), user);
    // }
    // });
    // var res = usersRepository.saveAll(users);
    // return res.stream().map(user -> mapper.toUserDTO(user)).toList();
    // }

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
