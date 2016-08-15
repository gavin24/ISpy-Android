package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.User;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public interface UserService {
    User addUser(User animal);
    User updateUser( User animal);
    User deleteUser( User animal);
    User getUser(Long d);
    ArrayList<User> getAllUsers( );

    void removeAllUsers();
}
