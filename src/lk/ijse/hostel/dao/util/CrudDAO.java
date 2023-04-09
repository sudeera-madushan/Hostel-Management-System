package lk.ijse.hostel.dao.util;

import java.util.List;

public interface CrudDAO<T, ID> extends SuperDAO {
    boolean save(T entity);

    boolean delete(T entity);

    boolean update(T entity);

    List<T> getAll();

    String getLastId();
}
