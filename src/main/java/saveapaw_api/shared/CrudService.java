package saveapaw_api.shared;

import java.util.List;

public interface CrudService<Q, C, U, ID> {

    public abstract List<Q> getMany();

    public abstract Q getOne(ID id);

    public abstract Q create(C dto);

    public abstract List<Q> createMany(List<C> dtos);

    public abstract Q update(ID id, U dto);

    public abstract void deleteAll();

    public abstract void deleteById(ID id);

    public abstract void deleteManyById(List<ID> ids);

}
