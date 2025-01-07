package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.UserDto;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao{
    public UserDto checkUser(String username, String password) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from user");

        UserDto userDto = new UserDto();

        while (rst.next()){
            if(rst.getString("password").equals(password) && rst.getString("username").equals(username)){
               userDto.setUsId(rst.getString(1));
               userDto.setRole(rst.getString(2));
               userDto.setPassword(rst.getString(3));
               userDto.setUsername(rst.getString(4));
            }
        }
        return userDto;

    }
}
