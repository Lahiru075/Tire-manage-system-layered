package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.UserDto;
import lk.ijse.gdse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao extends CrudDao<User>{
    User checkUser(String username, String password) throws SQLException;
}
