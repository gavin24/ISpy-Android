package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Story;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface StoryService {
    Story addStory(Story animal);
    Story updateStory( Story animal);
    Story deleteStory( Story animal);
    Story getStory(Long d);
    ArrayList<Story> getAllStorys( );

    void removeAllStorys();
}
