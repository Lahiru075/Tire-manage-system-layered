package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dao.CrudDao;
import lk.ijse.gdse.entity.User;

import java.sql.SQLException;

public interface UserDao extends CrudDao<User> {
    User checkUser(String username, String password) throws SQLException;
}
