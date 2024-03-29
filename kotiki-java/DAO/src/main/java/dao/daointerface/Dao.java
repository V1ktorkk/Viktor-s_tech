package dao.daointerface;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    void redeem(T t);

    void update(T t);

    void delete(T t);

    T getById(int id);
}