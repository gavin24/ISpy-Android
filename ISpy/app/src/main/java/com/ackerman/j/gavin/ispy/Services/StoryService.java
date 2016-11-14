package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Story;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface StoryService {
    Story addStory(Story story);
    Story updateStory( Story story);
    Story deleteStory( Story story);
    Story getStory(Long id);
    ArrayList<Story> getAllStorys( );

    void removeAllStorys();
}
