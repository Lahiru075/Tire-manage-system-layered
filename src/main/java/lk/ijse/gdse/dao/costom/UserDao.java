package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dao.CrudDao;
import lk.ijse.gdse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao extends CrudDao<User> {
    ArrayList<User> checkUser() throws SQLException;
}
