package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.UserDao;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select usId from user order by usId desc limit 1");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
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

    public ArrayList<User> checkUser() throws SQLException {
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
}
