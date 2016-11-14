package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Image;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface ImageService {
    Image addImage(Image image);
    Image updateImage( Image image);
    Image deleteImage( Image image);
    Image getImage(Long id);
    ArrayList<Image> getAllImages( );

    void removeAllImages();
}
