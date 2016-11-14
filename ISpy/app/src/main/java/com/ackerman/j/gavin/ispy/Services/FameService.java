package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Fame;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface FameService {
    Fame addFame(Fame fame);
    Fame updateFame( Fame fame);
    Fame deleteFame( Fame fame);
    Fame getFame(Long id);
    ArrayList<Fame> getAllFames( );

    void removeAllFames();
}
