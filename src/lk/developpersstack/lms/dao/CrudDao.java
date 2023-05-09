package lk.developpersstack.lms.dao;

import java.sql.SQLException;

public interface CrudDao<T,ID> {
    public void save(T t) throws SQLException,ClassNotFoundException;
    public void update(T t) throws SQLException,ClassNotFoundException;
    public T find(ID id) throws SQLException,ClassNotFoundException;
    public void delete(ID id) throws SQLException,ClassNotFoundException;
    public void findAll() throws SQLException,ClassNotFoundException;
}
