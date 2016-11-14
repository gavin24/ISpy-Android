package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Tag;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface TagService {
    Tag addTag(Tag tag);
    Tag updateTag( Tag tag);
    Tag deleteTag( Tag tag);
    Tag getTag(Long id);
    ArrayList<Tag> getAllTags( );

    void removeAllTags();
}
