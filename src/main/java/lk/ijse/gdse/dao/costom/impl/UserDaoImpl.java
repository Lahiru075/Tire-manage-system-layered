package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.UserDao;
import lk.ijse.gdse.dto.UserDto;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select usId from user order by usId desc limit 1");
        if (rst.next()){
            String usId = rst.getString(1);
            String subString = usId.substring(1);
            int value = Integer.parseInt(subString);
            int newIdIndex = value + 1; // Increment the number by 1
            return String.format("U%03d", newIdIndex);
        }
        return "U001";
    }

    public boolean save(User user) throws SQLException {
        return CrudUtil.execute("insert into user values (?,?,?,?)",
                user.getUsId(),
                user.getRole(),
                user.getPassword(),
                user.getUsername()
        );
    }

    public ArrayList<User> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from user");

        ArrayList<User> users = new ArrayList<>();

        while (rst.next()) {
            User user = new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            users.add(user);
        }
        return users;
    }

    public boolean update(User user) throws SQLException {
        return CrudUtil.execute("update user set role = ?, username = ?, password = ? where usId = ?",
                user.getRole(),
                user.getUsername(),
                user.getPassword(),
                user.getUsId()
        );
    }

    public boolean delete(String userId) throws SQLException {
        return CrudUtil.execute("delete from user where usId = ?", userId);
    }

    public User checkUser(String username, String password) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from user");

        User user = new User();

        while (rst.next()){
            if(rst.getString("password").equals(password) && rst.getString("username").equals(username)){
                user.setUsId(rst.getString(1));
                user.setRole(rst.getString(2));
                user.setPassword(rst.getString(3));
                user.setUsername(rst.getString(4));
            }
        }
        return user;

    }
}
