package lk.ijse.gdse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T> extends SuperDao{
    String getNextId() throws SQLException;
    ArrayList<T> getAll() throws SQLException;
    boolean save(T dto) throws SQLException;
    boolean delete(String Id) throws SQLException;
    boolean update(T dto) throws SQLException;
}
