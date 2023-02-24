package repositories;

import data.interfaces.IDB;
import entities.User;
import repositories.interfaces.IUsersRepositories;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UsersRepositories implements IUsersRepositories {
    private final IDB db;
    public UsersRepositories(IDB db){this.db = db;}

    public boolean CreateUser(User user) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO tbl_users(name,surname,balance,login,password) VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setDouble(3, user.getBalance());
            st.setString(4, user.getLogin());
            st.setString(5, user.getPassword());

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    public User SignIn(User userToCheck){
        Connection con = null;
        User user = new User();
        try {
            con = db.getConnection();
            String sql = "SELECT id,login,password,name,surname,balance FROM tbl_users";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (userToCheck.getPassword().equals(rs.getString("password")) &&
                        userToCheck.getLogin().equals(rs.getString("login"))) {
                    user = new User(rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getDouble("balance"));
                    return user;
                }
            }
            return user;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
    public boolean isExist(User user){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT login,password FROM tbl_users";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<User> users = new LinkedList<>();
            while (rs.next()){
                if (user.getPassword().equals(rs.getString("password")) &&
                        user.getLogin().equals(rs.getString("login"))){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public List<User> getAllUsers() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,login,password,name,surname,balance FROM tbl_users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new LinkedList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDouble("balance"));

                users.add(user);
            }

            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
