package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.UserDto;

import java.sql.SQLException;

public interface LoginDao {
    UserDto checkUser(String username, String password) throws SQLException;
}
