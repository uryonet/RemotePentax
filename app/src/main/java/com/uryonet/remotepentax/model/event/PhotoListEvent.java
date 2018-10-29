package com.uryonet.remotepentax.model.event;

import com.uryonet.remotepentax.model.entity.PhotoList;

public class PhotoListEvent {

    public final PhotoList photoList;

    public PhotoListEvent(PhotoList photoList) {
        this.photoList = photoList;
    }

}
