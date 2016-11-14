package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.User;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public interface UserService {
    User addUser(User user);
    User updateUser( User user);
    User deleteUser( User user);
    User getUser(Long id);
    ArrayList<User> getAllUsers( );
    boolean isAuthentic(String userName,String Password);
    void removeAllUsers();
}
