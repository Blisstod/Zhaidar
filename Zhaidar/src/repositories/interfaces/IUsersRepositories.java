package repositories.interfaces;

import entities.User;

import java.util.List;

public interface IUsersRepositories {
    boolean CreateUser(User user);
    User SignIn(User userToCheck);
    boolean isExist(User user);
    List<User> getAllUsers();
}
