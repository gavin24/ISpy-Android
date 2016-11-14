package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Tag;
import com.ackerman.j.gavin.ispy.Domain.UserTags;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-08-14.
 */
public interface UserTagsService {
    UserTags addUserTags(UserTags userTags);
    UserTags updateUserTags( UserTags userTags);
    UserTags deleteUserTags(UserTags userTags);
    UserTags getUserTags(Long id);
    ArrayList<UserTags> getAllUserTags( );

    void removeAllUserTags();
}
